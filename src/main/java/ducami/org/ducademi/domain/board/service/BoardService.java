package ducami.org.ducademi.domain.board.service;

import ducami.org.ducademi.domain.board.dto.BoardRequestDTO;
import ducami.org.ducademi.domain.board.dto.BoardResponseDTO;
import ducami.org.ducademi.domain.board.entity.BoardEntity;
import ducami.org.ducademi.domain.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService { // 페이징 처리, 파일 추가, 강의 idx도 해야함
    private final BoardRepository boardRepository;

    // 글 생성
    public BoardResponseDTO createBoard(BoardRequestDTO requestDTO) {
        BoardEntity board = BoardEntity.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .build();
        boardRepository.save(board);
        return new BoardResponseDTO(board);
    }

    // 글 하나 조회
    public BoardResponseDTO findOneBoard(Long boardIdx) {
        BoardEntity board = boardRepository.findById(boardIdx).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        return new BoardResponseDTO(board);
    }

    // 게시글 수정
    @Transactional
    public Long update(Long boardIdx, BoardRequestDTO requestDTO) {
        BoardEntity board = boardRepository.findById(boardIdx).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        board.update(requestDTO);
        return board.getBoardIdx();
    }

    // 게시글 삭제
    @Transactional
    public Long deleteBoard(Long boardIdx) {
        boardRepository.deleteById(boardIdx);
        return boardIdx;
    }


}
