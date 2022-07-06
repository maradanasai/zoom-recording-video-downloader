package com.example.downloader.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantAudioFile {
    private String id;
    private String recording_start;
    private String recording_end;
    private String file_type;
    private String file_name;
    private String file_size;
    private String file_extension;
    private String play_url;
    private String download_url;
    private String status;
    private String recording_type;
}