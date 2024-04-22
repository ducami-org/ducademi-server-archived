package ducami.org.ducademi.domain.board.controller;

import ducami.org.ducademi.domain.board.dto.CreateBoardRequestDTO;
import ducami.org.ducademi.domain.board.dto.BoardResponseDTO;
import ducami.org.ducademi.domain.board.dto.GetBoardsResponseDTO;
import ducami.org.ducademi.domain.board.service.BoardService;
import ducami.org.ducademi.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 글 전체 조회
    @GetMapping("lectures/{idx}")
    public List<GetBoardsResponseDTO> getBoards(
            @PathVariable Long idx
    ){
        return boardService.getBoards(idx);
    }


    // 글 등록
    @PostMapping()
    public BaseResponse<?> createBoard(
            @RequestBody CreateBoardRequestDTO requestDTO
    ) {
        return boardService.createBoard(requestDTO);
    }

    // 글 하나 조회
    @GetMapping("{idx}")
    public BoardResponseDTO getBoard(@PathVariable Long idx) {
        return boardService.findOneBoard(idx);
    }

    // 글 수정
    @PutMapping("{idx}")
    public Long updateBoard(@PathVariable Long idx, @RequestBody CreateBoardRequestDTO requestDTO) {
        return boardService.update(idx, requestDTO);
    }

    // 글 삭제
    @DeleteMapping("{idx}")
    public BaseResponse<?> deleteBoard(@PathVariable Long idx) {
        return boardService.deleteBoard(idx);
    }

}