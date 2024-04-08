package ducami.org.ducademi.domain.lecture.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LectureType {
    WEB("웹"),
    ANDROID("안드로이드"),
    IOS("iOS"),
    SERVER("서버"),
    PYTHON("파이썬"),
    C("C언어"),
    KOTLIN("코틀린"),
    SWIFT("스위프트"),
    ARDUINO("아두이노"),
    AI("인공지능"),
    ROBOT("로봇"),
    GAME("게임");

    private final String category;
}
