package ru.netology.Manager;

import ru.netology.Repository.TicketRepository;
import ru.netology.domain.Ticket;

import java.util.Arrays;

public class TicketManager {
    public TicketRepository repo = new TicketRepository();

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket newTicket) {
        repo.save(newTicket);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    public boolean matches(Ticket ticket, String from, String to) {
        if (ticket.getDeptAirport().equals(from) & ticket.getDestAirport().equals(to)) {
            return true;
        } else {
            return false;
        }
    }
/*    public Ticket[] searchBy(String from,String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] temp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    temp[i] = result[i];
                }
                temp[temp.length - 1] = ticket;
                result = temp;
            }
        }
        Arrays.sort(result);
        return result;
    }*/

    public Ticket[] searchBy2(String from, String to) {
        Ticket[] result = new Ticket[0];
        TicketRepository temp = new TicketRepository();
        for (Ticket ticket : repo.findAll()) {
            if (matches(ticket, from, to)) {
                temp.save(ticket);
            }
            result = temp.findAll();
        }
        Arrays.sort(result);
        return result;
    }

}
