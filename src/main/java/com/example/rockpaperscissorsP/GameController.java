package com.example.rockpaperscissorsP;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class GameController {

    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html로 연결
    }

    @GetMapping("/play")
    public String playGame(@RequestParam("userChoice") String userChoice, Model model) {
        // 컴퓨터 선택 (랜덤)
        String[] choices = {"rock", "paper", "scissors"};
        String computerChoice = choices[new Random().nextInt(choices.length)];

        // 결과 계산
        String result = determineWinner(userChoice, computerChoice);

        // 로그 출력 (여기에 추가)
        System.out.println("사용자 선택: " + userChoice);
        System.out.println("컴퓨터 선택: " + computerChoice);
        System.out.println("결과: " + result);

        // 모델에 데이터 전달
        model.addAttribute("userChoice", userChoice);
        model.addAttribute("computerChoice", computerChoice);
        model.addAttribute("result", result);

        return "result"; // templates/result.html로 연결
    }

    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "무승부!";
        }
        if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return "승리!";
        }
        return "패배!";
    }
}
