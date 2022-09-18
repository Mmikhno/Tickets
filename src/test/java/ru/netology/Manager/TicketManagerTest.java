package ru.netology.Manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Comparator.TicketByTimeComparator;
import ru.netology.Repository.TicketRepository;
import ru.netology.domain.Ticket;

import java.util.Arrays;

public class TicketManagerTest {
    public TicketRepository repo = new TicketRepository();
    public TicketManager manager = new TicketManager(repo);
    TicketByTimeComparator timeComparator = new TicketByTimeComparator();

    Ticket ticket1 = new Ticket(1, 1000, "DME", "BQT", 120);
    Ticket ticket2 = new Ticket(2, 8000, "DME", "LED", 120);
    Ticket ticket3 = new Ticket(3, 3000, "KSN", "MSQ", 150);
    Ticket ticket4 = new Ticket(4, 4000, "LED", "SVO", 100);
    Ticket ticket5 = new Ticket(5, 5000, "DME", "LED", 400);
    Ticket ticket6 = new Ticket(6, 1000, "DME", "LED", 79);
    Ticket ticket7 = new Ticket(7, 7000, "DME", "LED", 80);
    Ticket ticket8 = new Ticket(8, 4000, "DME", "LED", 95);
    Ticket ticket9 = new Ticket(9, 4000, "DME", "LED", 105);
    Ticket ticket10 = new Ticket(10, 4000, "DME", "LED", 95);

    @Test
    public void shouldSearchBy() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {ticket6, ticket8, ticket5, ticket7, ticket2};
        Ticket[] actual = manager.searchBy2("DME", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    //поиск в пустом массиве
    public void shouldSearchIfEmptyArray() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy2("DME", "LED");
    }

    @Test
    //не найден подходящий результат
    public void shouldSearchNoResult() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy2("ATN", "DME");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    //2 билета с одинаковой ценой
    public void shouldSearchIfEqualPricesExist() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        Ticket[] expected = {ticket6, ticket8, ticket9, ticket5, ticket7, ticket2};
        Ticket[] actual = manager.searchBy2("DME", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    //TESTS FOR COMPARATOR
    @Test
    //сортировка по времени в пути
    public void shouldSearchSortByTime() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        Ticket[] expected = {ticket6, ticket7, ticket8, ticket9, ticket2, ticket5};
        Ticket[] actual = manager.searchByTime("DME", "LED", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSortByTime2() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.searchByTime("KSN", "MSQ", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSortByTimeDuplicatesExist() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
        manager.add(ticket10);
        Ticket[] actual = manager.searchByTime("DME", "LED", timeComparator);
        Ticket[] expected = {ticket6, ticket7, ticket8, ticket10, ticket9, ticket2, ticket5};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSortByTimeEmptyArray() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchByTime("DME", "LED", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
