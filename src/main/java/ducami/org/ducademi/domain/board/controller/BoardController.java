package ducami.org.ducademi.domain.board.controller;

import ducami.org.ducademi.domain.board.dto.BoardRequestDTO;
import ducami.org.ducademi.domain.board.dto.BoardResponseDTO;
import ducami.org.ducademi.domain.board.dto.GetBoardsResponseDTO;
import ducami.org.ducademi.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

//    public BoardController(BoardService boardService) {
//        this.boardService = boardService;
//    }

    // 글 전체 조회
    @GetMapping("lectures/{idx}")
    public List<GetBoardsResponseDTO> getBoards(
            @PathVariable Long idx
    ){
        return boardService.getBoards(idx);
    }


    // 글 등록
    @PostMapping()
    public BoardResponseDTO createBoard(
            @RequestHeader("Authorization") String token,
            @RequestBody BoardRequestDTO requestDTO
    ) {
        BoardResponseDTO board = boardService.createBoard(token,requestDTO);
        return board;
    }

    // 글 하나 조회
    @GetMapping("{idx}")
    public BoardResponseDTO getBoard(@PathVariable Long idx) {
        return boardService.findOneBoard(idx);
    }

    // 글 수정
    @PutMapping("{idx}")
    public Long updateBoard(@PathVariable Long idx, @RequestBody BoardRequestDTO requestDTO) {
        return boardService.update(idx, requestDTO);
    }

    // 글 삭제
    @DeleteMapping("{idx}")
    public Long deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }

}
