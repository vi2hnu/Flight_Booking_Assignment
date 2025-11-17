package org.example.booking.service;

import org.example.booking.model.entity.Ticket;
import org.example.booking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketDetailsService implements TicketDetailsInterface {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket findTicketByPnr(String pnr) {
        return ticketRepository.findTicketByPnr(pnr);
    }
}
