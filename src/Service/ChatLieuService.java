/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.ChatLieu;
import Interface.ChatLieuimpl;
import Repo.ChatLieuRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ChatLieuService implements ChatLieuimpl {
    
    ChatLieuRepository chatLieuRepository = new ChatLieuRepository();
    
    @Override
    public ArrayList<ChatLieu> getALlChatLieus() {
        return chatLieuRepository.getallChatLieus();
    }
    
    @Override
    public boolean insert(ChatLieu cl) {
        return chatLieuRepository.insertChatLieu(cl);
    }
    
    @Override
    public boolean update(ChatLieu cl) {
        return chatLieuRepository.updateChatLieu(cl);
    }
    
    @Override
    public ChatLieu getIDByName(String chatLieu) {
        return chatLieuRepository.getChatLieuByID(chatLieu);
    }
    
    @Override
    public void updateTrangThai(String idChatLieu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
