package org.jaehoonman.wschatdemo.controller;

import lombok.RequiredArgsConstructor;
import org.jaehoonman.wschatdemo.model.ChatRoom;
import org.jaehoonman.wschatdemo.service.ChatRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat") // Controller 호출의 prefix를 설정
public class ChatRoomController {

    /**
     *  채팅방 핸들링을 위한 컨트롤러
     *  생성, 채팅 조회 등의 기능을 가진다.
     */

    private final ChatRoomService chatRoomService;

    /**
     * 채팅 메인 화면 렌더링
     * '@RequestMapping("/chat")'를 설정했기에 이를 호출하기 위해
     * '/chat/room' 의 경로로 호출해야한다.
     * @param model
     * @return
     */
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room-list";
    }

    /**
     * 채팅방 생성
     * POST 호출 시 Body에 방제목을 담아서 이를 토대로 방을 생성한다.
     */
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestBody Map<String, String> requestBody) {

        String roomName = requestBody.get("roomName");

        // 채팅방 생성 시 ChatRoom Model의 생성자를 이용한다.
        return chatRoomService.createRoom(roomName);
    }

    /**
     * 활성화된 웹 소켓 채팅 방 리스트 반환
     * 메인 화면에서 WS 연결 수립(방생성) 시마다 호출한다.
     */
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        /*
            방 생성 시 Map<String, ChatRoom>으로 메모리에 상주 시킨 데이터를
            ArrayList 향태로가져온다.
         */
        return chatRoomService.findAllRoom();
    }


    /**
     * 채팅방 입장 화면 렌더링.
     * 호출 시 ID 값을 Detail 화면으로 토스한다.
    */
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/room-detail";
    }

    /**
     * 채팅방 입장 시 넘겨 받은 ID 값으로 방제목과 같은 상세 정보를 구해온다.
    */
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomService.findRoomById(roomId);
    }
}
