package com.comp_alumno;

import com.comp_alumno.entity.Ticket;
import com.comp_alumno.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void testSaveAndFindByName() {
        Ticket ticket = new Ticket(null, "Ticket Soporte");
        ticketRepository.save(ticket);

        List<Ticket> result = ticketRepository.findByNameContainingIgnoreCase("soporte");

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).containsIgnoringCase("soporte");
    }
}