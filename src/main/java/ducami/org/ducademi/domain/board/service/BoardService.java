package ducami.org.ducademi.domain.board.service;

import ducami.org.ducademi.domain.board.dto.CreateBoardRequestDTO;
import ducami.org.ducademi.domain.board.dto.BoardResponseDTO;
import ducami.org.ducademi.domain.board.dto.GetBoardsResponseDTO;
import ducami.org.ducademi.domain.board.entity.BoardEntity;
import ducami.org.ducademi.domain.board.repository.BoardRepository;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import ducami.org.ducademi.global.auth.JwtUtils;
import ducami.org.ducademi.global.exception.CustomException;
import ducami.org.ducademi.global.response.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ducami.org.ducademi.global.exception.CustomErrorCode.BOARD_NOT_EXIST;
import static ducami.org.ducademi.global.exception.CustomErrorCode.MEMBER_NOT_CORRECT;
import static ducami.org.ducademi.global.exception.CustomErrorCode.MEMBER_NOT_EXIST;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService { // 페이징 처리, 파일 추가
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
    public BaseResponse<?> createBoard(CreateBoardRequestDTO requestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MemberEntity member = memberRepository.findById(authentication.getName())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_EXIST));

        BoardEntity board = BoardEntity.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .memberIdx(member.getIdx())
                .lectureIdx(requestDTO.getLectureIdx())
                .build();
        boardRepository.save(board);

        return BaseResponse.of(
                true,
                "OK",
                "보드 생성 성공 !!",
                null
        );
    }

    // 글 하나 조회
    public BoardResponseDTO findOneBoard(Long boardIdx) {
        BoardEntity board = boardRepository.findById(boardIdx).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        return BoardResponseDTO.of(board);
    }

    // 게시글 수정
    @Transactional
    public Long update(Long boardIdx, CreateBoardRequestDTO requestDTO) {
        BoardEntity board = boardRepository.findById(boardIdx).orElseThrow(
                () -> new CustomException(BOARD_NOT_EXIST)
        );
        board.update(requestDTO);
        return board.getBoardIdx();
    }

    // 게시글 삭제
    @Transactional
    public BaseResponse<?> deleteBoard(Long boardIdx) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MemberEntity member = memberRepository.findById(authentication.getName())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_EXIST));

        BoardEntity board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new CustomException(BOARD_NOT_EXIST));

        if (!board.getMemberIdx().equals(member.getIdx())) {
            throw new CustomException(MEMBER_NOT_CORRECT);
        }

//        BoardEntity board
        boardRepository.deleteById(boardIdx);
        return BaseResponse.of(
                true,
                "OK",
                "보드 생성 성공 !!",
                null
        );
    }


}
