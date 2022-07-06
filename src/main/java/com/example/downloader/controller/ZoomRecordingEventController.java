package com.example.downloader.controller;

import com.example.downloader.model.ZoomRecordingCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/miscellaneous")
@Slf4j
public class ZoomRecordingEventController {

    @Value("${file.storage.basepath}")
    private String basePath;

    @PostMapping("/event/zoomrecordingcompleted")
    public void zoomRecordingCompletedWebHook(@RequestBody ZoomRecordingCompletedEvent event) {
        CompletableFuture.supplyAsync(() -> {
            downloadZoomRecordingVideo(event);
            return null;
        })
                .exceptionally(e -> {
                    log.error("something went wrong in zoomRecordingCompletedWebHook", e);
                    return null;
                });
    }

    private void downloadZoomRecordingVideo(ZoomRecordingCompletedEvent event) {
        final String downloadAccessToken = event.getDownloadToken();
        event.getPayload()
                .getObject()
                .getRecordingFiles()
                .stream()
                .peek(e -> e.setDownloadUrl(String.format("%s?access_token=%s", e.getDownloadUrl(), downloadAccessToken)))
                .forEach(e -> {
                    final String fileName = Paths.get(basePath, event.getPayload().getObject().getTopic() + "." + e.getFileExtension()).toString();
                    try (ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(e.getDownloadUrl()).openStream());
                         FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                        fileOutputStream.getChannel()
                                .transferFrom(readableByteChannel, 0, e.getFileSize());
                        log.info("successfully downloaded the video: " + fileName);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }
}
