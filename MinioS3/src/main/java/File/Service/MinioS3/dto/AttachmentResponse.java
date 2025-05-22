package File.Service.MinioS3.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentResponse {

    Long id;
    String name;
    String url;
    Long lessonId;
    LocalDateTime createdTime;

}