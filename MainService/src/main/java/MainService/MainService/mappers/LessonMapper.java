package MainService.MainService.mappers;

import MainService.MainService.dto.LessonDto;
import MainService.MainService.entities.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "chapterId", source = "chapter.id")
    @Mapping(target = "attachmentId", source = "attachmentId")
    LessonDto toDto(Lesson lesson);


    @Mapping(target = "chapter", ignore = true)
    Lesson toEntity(LessonDto lessonDto);

    List<LessonDto> toDtoList(List<Lesson> lessons);

}
