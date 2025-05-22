package File.Service.MinioS3.api;

import File.Service.MinioS3.dto.AttachmentResponse;
import File.Service.MinioS3.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PostMapping("/file/upload")
    public AttachmentResponse upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("lessonId") Long lessonId) {
        return fileService.upload(file, lessonId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/file/lesson/{lessonId}")
    public List<AttachmentResponse> getByLessonId(@PathVariable Long lessonId) {
        return fileService.getAttachmentsByLessonId(lessonId);
    }
}
