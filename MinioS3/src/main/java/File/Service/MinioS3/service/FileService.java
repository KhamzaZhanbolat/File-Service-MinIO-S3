package File.Service.MinioS3.service;

import File.Service.MinioS3.dto.AttachmentResponse;
import File.Service.MinioS3.model.Attachment;
import File.Service.MinioS3.rep.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final AttachmentRepository attachmentRepository;
    private final MinioService minioService;

    @Value("${minio.bucket}")
    private String bucket;

    public AttachmentResponse upload(MultipartFile file, Long lessonId) {
        // Сохраняем файл в MinIO (или в другом месте)
        String fileName = file.getOriginalFilename();
        String fileUrl = minioService.uploadFile(file); // допустим, ты реализовал MinIO сервис

        // Сохраняем Attachment в БД
        Attachment attachment = new Attachment();
        attachment.setName(fileName);
        attachment.setUrl(fileUrl);
        attachment.setCreatedTime(LocalDateTime.now());
        attachment.setLessonId(lessonId); // если Lesson — это внешний ID

        attachmentRepository.save(attachment);

        return new AttachmentResponse(
                attachment.getId(),
                attachment.getName(),
                attachment.getUrl(),
                attachment.getLessonId(),
                attachment.getCreatedTime()
        );
    }

    public List<AttachmentResponse> getAttachmentsByLessonId(Long lessonId) {
        return attachmentRepository.findAllByLessonId(lessonId)
                .stream()
                .map(attachment -> new AttachmentResponse(
                        attachment.getId(),
                        attachment.getName(),
                        attachment.getUrl(),
                        attachment.getLessonId(),
                        attachment.getCreatedTime()
                ))
                .toList();
    }

}