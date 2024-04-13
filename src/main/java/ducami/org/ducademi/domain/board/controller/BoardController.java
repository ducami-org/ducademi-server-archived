package ducami.org.ducademi.domain.board.controller;

import ducami.org.ducademi.domain.board.dto.BoardRequestDTO;
import ducami.org.ducademi.domain.board.dto.BoardResponseDTO;
import ducami.org.ducademi.domain.board.service.BoardService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    // 글 등록
    @PostMapping("/boards")
    public BoardResponseDTO createBoard(@RequestBody BoardRequestDTO requestDTO) {
        BoardResponseDTO board = boardService.createBoard(requestDTO);
        return board;
    }

    // 글 하나 조회
    @GetMapping("/boards/{id}")
    public BoardResponseDTO getBoard(@PathVariable Long idx) {
        return boardService.findOneBoard(idx);
    }

    // 글 수정
    @PutMapping("/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDTO requestDTO) {
        return boardService.update(id, requestDTO);
    }

    // 글 삭제
    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }

}
