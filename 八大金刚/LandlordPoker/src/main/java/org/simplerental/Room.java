package org.simplerental;

import java.util.*;

public class Room {

    private List<Card> cards = new ArrayList<>();

    {
        String[] sizes = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        String[] colors = {"♥", "♦", "♣", "♠"};
        int num = 0;

        for (String size: sizes){
            num++;
            for (String color:colors){
                Card card = new Card(size, color,num);
                cards.add(card);
            }
        }
//        cards.add(new Card("", "🃏", ++num));
//        cards.add(new Card("", "👲", ++num));
        Collections.addAll(cards, new Card("", "🃏", ++num), new Card("", "👲", ++num));
        System.out.println(cards);
    }

    public void start() {
        Collections.shuffle(cards);
        System.out.println("shuffled Cards" + cards);

        Map<String, List<Card>> players = new HashMap<>();
        List<Card> lhc = new ArrayList<>();
        players.put("lhc",lhc);

        List<Card> lhb = new ArrayList<>();
        players.put("lhb",lhb);

        List<Card> lhz = new ArrayList<>();
        players.put("lhz",lhz);

        for (int i = 0; i < cards.size() - 3; i++) {

            Card card = cards.get(i);
            if(i%3==0){
                lhc.add(card);
            }else if (i%3 == 1){
                lhb.add(card);
            }else if (i%3 == 2){
                lhz.add(card);
            }
        }


        List<Card> lastcards = cards.subList(cards.size()-3, cards.size());
        System.out.println("lastcard: " + lastcards);

        lhz.addAll(lastcards);

//        sortCards(lhc);
//        Collections.sort(lhb)
//        Collections.sort(lhz)
        sortCards(lhc);
        sortCards(lhb);
        sortCards(lhz);

        for (Map.Entry<String , List<Card>> entry : players.entrySet()){
            String name = entry.getKey();
            List<Card> cards = entry.getValue();
            System.out.println(name+"'s cards"+ cards);
        }
    }

    private void sortCards(List<Card> cards) {
        Collections.sort(cards,new Comparator<Card>() {

            @Override
            public int compare(Card o1, Card o2) {
                return o2.getNum()- o1.getNum();
            }
        });

    }

}
