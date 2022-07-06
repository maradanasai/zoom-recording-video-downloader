package com.example.downloader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ZoomRecordingCompletedEvent {
    private String event;

    @JsonProperty("event_ts")
    private String eventTs;

    private Payload payload;

    @JsonProperty("download_token")
    private String downloadToken;
}

