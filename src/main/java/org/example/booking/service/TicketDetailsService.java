package org.example.booking.service;

import org.example.booking.model.entity.History;
import org.example.booking.model.entity.Ticket;
import org.example.booking.model.entity.Users;
import org.example.booking.repository.HistoryRepository;
import org.example.booking.repository.TicketRepository;
import org.example.booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketDetailsService implements TicketDetailsInterface {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Ticket findTicketByPnr(String pnr) {
        return ticketRepository.findTicketByPnr(pnr);
    }

    @Override
    public List<History> findHistoryByEmail(String email) {
        Users user = usersRepository.findByEmail(email);
        return historyRepository.findAllByUsers_Id(user.getId());
    }
}
