package ducami.org.ducademi.domain.lecture.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LectureListResponse {
    private Integer currentPage;
    private Boolean hasMorePage;
    private List<LectureResponse> list;
}