package com.example.downloader.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Payload {
    private String account_id;
    private String cloud_recording_storage_used_percentage;
    private Object object;
}