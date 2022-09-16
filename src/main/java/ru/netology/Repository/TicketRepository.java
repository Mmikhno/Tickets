package ru.netology.Repository;

import ru.netology.domain.Ticket;

public class TicketRepository {
    protected Ticket[] tickets = new Ticket[0];

    public void save(Ticket newTicket) {
        Ticket[] temp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            temp[i] = tickets[i];
        }
        temp[temp.length - 1] = newTicket;
        tickets = temp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public void removeById(int id) {
        Ticket[] temp = new Ticket[tickets.length - 1];
        int index = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                temp[index] = ticket;
                index++;
            }
        }
        tickets = temp;
    }

    public void removeById2(int id) {
        Ticket[] temp = new Ticket[tickets.length - 1];
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i].getId() != id) {
                temp[i] = tickets[i];
            }
        }
        tickets = temp;
    }

    public Ticket findById(int id) {
        Ticket result = null;
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                result = ticket;
            }
        }
        return result;
    }
}
