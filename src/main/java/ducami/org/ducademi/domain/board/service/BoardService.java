package ducami.org.ducademi.domain.board.service;

import ducami.org.ducademi.domain.board.dto.BoardRequestDTO;
import ducami.org.ducademi.domain.board.dto.BoardResponseDTO;
import ducami.org.ducademi.domain.board.dto.GetBoardsResponseDTO;
import ducami.org.ducademi.domain.board.entity.BoardEntity;
import ducami.org.ducademi.domain.board.repository.BoardRepository;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import ducami.org.ducademi.global.auth.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService { // 페이징 처리, 파일 추가, 강의 idx도 해야함
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;

    // 글 전체 조회
    public List<GetBoardsResponseDTO> getBoards(Long idx){ // 강의 IDX
        // 1. List<BoardEntity>
        List<BoardEntity> boardList = boardRepository.findAllByLectureIdx(idx);

        // 2. response로 변환
        return boardList.stream().map(
                GetBoardsResponseDTO::of
        ).toList();
    }

    // 글 생성
    public BoardResponseDTO createBoard(String token, BoardRequestDTO requestDTO) {
        MemberEntity member = memberRepository.findByEmail(jwtUtils.getEmail(token))
                .orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다."));

        BoardEntity board = BoardEntity.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .memberIdx(member.getIdx())
                .lectureIdx(requestDTO.getLectureIdx())
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
