package com.example.downloader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Object {
    private String id;
    private String uuid;
    private String host_id;
    private String account_id;
    private String password;
    private String topic;
    private String type;
    private String start_time;
    private String timezone;
    private String host_email;
    private String duration;
    private String share_url;

    @JsonProperty("total_size")
    private String total_size;

    private String recording_count;
    private String on_prem;

    @JsonProperty("thumbnail_links")
    private List<String> thumbnailLinks;

    @JsonProperty("recording_files")
    private List<RecordingFile> recordingFiles;

    @JsonProperty("participant_audio_files")
    private List<ParticipantAudioFile> participantAudioFiles;
}