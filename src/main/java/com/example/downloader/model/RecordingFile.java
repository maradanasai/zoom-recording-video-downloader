package com.example.downloader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordingFile {
    private String id;

    @JsonProperty("meeting_id")
    private String meetingId;

    @JsonProperty("recording_start")
    private String recordingStart;

    @JsonProperty("recording_end")
    private String recordingEnd;

    @JsonProperty("file_type")
    private String fileType;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_size")
    private Integer fileSize;

    @JsonProperty("file_extension")
    private String fileExtension;

    @JsonProperty("play_url")
    private String playUrl;

    @JsonProperty("download_url")
    private String downloadUrl;

    private String status;
    private String recording_type;
}