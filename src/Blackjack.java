import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
public class Blackjack {
    Integer somaDealer = 0;
    Integer asDealer = 0;
    Integer somaJogador = 0;
    Integer asJogador = 0;

    Carta cartaVirada;
    private class Carta {
        String valor;
        String tipo;


        Carta(String valor, String tipo) {
            this.valor = valor;
            this.tipo = tipo;
        }

        public String toString(){
            return valor + "_" + tipo;
        }

        public int getValue(){
            if ("AJQK".contains(valor)){
                if (valor == "A"){
                    return 11;
                }
                return 10;
            }
            return Integer.parseInt(valor);
        }

        public boolean eAs(){
            return valor == "A";
        }

        public String getImagePath(){
            return "./cartas/" + toString() + ".png";
        }
    }

    ArrayList<Carta> deck;
    ArrayList<Carta> maoDealer = new ArrayList<Carta>();
    ArrayList<Carta> maoJogador = new ArrayList<Carta>();
    Random random = new Random();

    //janela

    int janelaWidth = 800;
    int janelaHeight = 600;
    int cartaWidth = 110;
    int cartaHeight = 154;


    JFrame frame = new JFrame("Blackjack");
    JPanel barraJogo = new JPanel(){
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            try{
                //carta virada
                Image cartaViradaImagem = new ImageIcon(getClass().getResource("./cartas/costas.png")).getImage();
                if (!botaoStand.isEnabled()){
                    cartaViradaImagem = new ImageIcon(getClass().getResource(cartaVirada.getImagePath())).getImage();
                }
                g.drawImage(cartaViradaImagem, 20, 20, cartaWidth, cartaHeight, null);

                //dealer
                for (int i=0;i<maoDealer.size();i++){
                    Carta carta = maoDealer.get(i);
                    Image cartaImagem = new ImageIcon(getClass().getResource(carta.getImagePath())).getImage();
                    g.drawImage(cartaImagem,cartaWidth + 25 + (cartaWidth+5)*i,20, cartaWidth, cartaHeight, null);
                }

                if (!botaoStand.isEnabled()){
                    somaDealer = reduzirAsDealer();
                    somaJogador = reduzirAsJogador();
                    System.out.println("Stand:");
                    System.out.println(somaDealer);
                    System.out.println(somaJogador);

                    String mensagem = "";
                    if(somaJogador >21){
                        g.setColor(Color.red);
                        mensagem = "Derrota!";
                    }
                    else if(somaDealer >21){
                        g.setColor(Color.green);
                        mensagem = "Vitória!";
                    }
                    else if(somaJogador == somaDealer){
                        g.setColor(Color.white);
                        mensagem = "Empate!";
                    }
                    else if(somaJogador > somaDealer){
                        g.setColor(Color.green);
                        mensagem = "Vitória!";
                    }
                    else if(somaJogador < somaDealer){
                        g.setColor(Color.red);
                        mensagem = "Derrota!";
                    }
                    else if (maoJogador.size()==2){
                        if (asJogador==1){
                            if(somaJogador==21){
                                botaoHit.setEnabled(false);
                                if (maoDealer.size()>3){
                                    if (asDealer==1){
                                        if(somaDealer==21){
                                            g.setColor(Color.white);
                                            mensagem = "Empate!";
                                        }
                                        else{
                                            g.setColor(Color.green);
                                            mensagem = "Vitória!";
                                        }
                                    }
                                    else{
                                        g.setColor(Color.green);
                                        mensagem = "Vitória!";
                                    }
                                }
                                else{
                                    g.setColor(Color.green);
                                    mensagem = "Vitória!";
                                }
                            }
                        }
                    }
                    else if (maoDealer.size()<3){
                        if (asDealer==1){
                            if(somaDealer==21){
                                if (maoJogador.size()==2){
                                    if (asJogador==1){
                                        if(somaJogador==21){
                                            g.setColor(Color.white);
                                            mensagem = "Empate!";
                                        }
                                        else{
                                            g.setColor(Color.red);
                                            mensagem = "Derrota!";
                                        }
                                    }
                                    else{
                                        g.setColor(Color.red);
                                        mensagem = "Derrota!";
                                    }
                                }
                                else{
                                    g.setColor(Color.red);
                                    mensagem = "Derrota!";
                                }
                            }
                        }
                    }

                    g.setFont(new Font("Arial", Font.PLAIN,30));
                    g.drawString(mensagem, 330, 250);
                }
                else if(botaoStand.isEnabled()){
                    String mensagem = "";
                    if (maoJogador.size()==2){
                        if (asJogador==1){
                            if(somaJogador==21){
                                botaoHit.setEnabled(false);
                                if (maoDealer.size()<3){
                                    if (asDealer==1){
                                        if(somaDealer==21){
                                            g.setColor(Color.white);
                                            mensagem = "Empate!";
                                        }
                                        else{
                                            g.setColor(Color.green);
                                            mensagem = "Vitória!";
                                        }
                                    }
                                    else{
                                        g.setColor(Color.green);
                                        mensagem = "Vitória!";
                                    }
                                }
                                else{
                                    g.setColor(Color.green);
                                    mensagem = "Vitória!";
                                }
                            }
                        }
                    }
                    else if (maoDealer.size()<3){
                        if (asDealer==1){
                            if(somaDealer==21){
                                if (maoJogador.size()==2){
                                    if (asJogador==1){
                                        if(somaJogador==21){
                                            g.setColor(Color.white);
                                            mensagem = "Empate!";
                                        }
                                        else{
                                            g.setColor(Color.red);
                                            mensagem = "Derrota!";
                                        }
                                    }
                                    else{
                                        g.setColor(Color.red);
                                        mensagem = "Derrota!";
                                    }
                                }
                                else{
                                    g.setColor(Color.red);
                                    mensagem = "Derrota!";
                                }
                            }
                        }
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }

            //jogador
            for (int i=0;i<maoJogador.size();i++){
                Carta carta = maoJogador.get(i);
                Image cartaImagem = new ImageIcon(getClass().getResource(carta.getImagePath())).getImage();
                g.drawImage(cartaImagem,20 + (cartaWidth+5)*i,320, cartaWidth, cartaHeight, null);
            }
        }
    };
    JPanel botaoBarra = new JPanel();
    JButton botaoHit = new JButton("Puxar");
    JButton botaoStand = new JButton("Parar");
    JButton botaoReiniciar = new JButton("Reiniciar");

    Blackjack() {
        iniciar();

        frame.setVisible(true);
        frame.setSize(janelaWidth, janelaHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        barraJogo.setLayout(new BorderLayout());
        barraJogo.setBackground(new Color(53, 101, 77));
        frame.add(barraJogo);

        botaoHit.setFocusable(false);
        botaoBarra.add(botaoHit);
        botaoStand.setFocusable(false);
        botaoBarra.add(botaoStand);
        botaoReiniciar.setFocusable(false);
        botaoBarra.add(botaoReiniciar);
        botaoReiniciar.setEnabled(false);
        frame.add(botaoBarra,BorderLayout.SOUTH);

        botaoHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Carta carta = deck.remove(deck.size()-1);
                somaJogador += carta.getValue();
                asJogador += carta.eAs()? 1 : 0;
                maoJogador.add(carta);
                if(reduzirAsJogador() > 21){
                    botaoHit.setEnabled(false);
                }
                if(somaJogador==21){
                    botaoHit.setEnabled(false);
                }
                barraJogo.repaint();
            }
        });

        botaoStand.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                botaoHit.setEnabled(false);
                botaoStand.setEnabled(false);
                botaoReiniciar.setEnabled(true);

                while (somaDealer<17){
                    if (maoJogador.size()==2){
                        if (asJogador==1){
                            if(somaJogador==21){
                                break;
                            }
                        }
                    }
                    else if(somaDealer>=somaJogador){
                        break;
                    }
                    Carta carta = deck.remove(deck.size()-1);
                    somaDealer += carta.getValue();
                    asDealer += carta.eAs()? 1: 0;
                    maoDealer.add(carta);
                }
                barraJogo.repaint();
            }
        });

        botaoReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });
    }


    public void iniciar() {
        montarDeck();
        embaralhar();

        //dealer

        Carta cartaVirada = deck.remove(deck.size() - 1);
        this.cartaVirada=cartaVirada;
        somaDealer += cartaVirada.getValue();
        asDealer += cartaVirada.eAs() ? 1 : 0;

        Carta carta = deck.remove(deck.size() - 1);
        somaDealer += carta.getValue();
        asDealer += carta.eAs() ? 1 : 0;
        maoDealer.add(carta);

        System.out.println("Dealer:");
        System.out.println(cartaVirada);
        System.out.println(maoDealer);
        System.out.println(somaDealer);
        System.out.println(asDealer);



        //jogador

        for (int jogador_count = 0; jogador_count < 2; jogador_count++) {
            carta = deck.remove(deck.size()-1);
            somaJogador += carta.getValue();
            asJogador += carta.eAs() ? 1 : 0;
            maoJogador.add(carta);
        }

        System.out.println("Jogador:");
        System.out.println(maoJogador);
        System.out.println(somaJogador);
        System.out.println(asJogador);
        System.out.println(deck);

        if (maoDealer.size()<3){
            if (asDealer==1){
                if(somaDealer==21){
                    botaoHit.setEnabled(false);
                }
            }
        }
    }

    public void montarDeck() {
        deck = new ArrayList<Carta>();
        String[] valor = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] tipo = {"copas", "espadas", "ouros", "paus"};

        for (int tipo_count = 0; tipo_count < tipo.length; tipo_count++) {
            for (int valor_count = 0; valor_count < valor.length; valor_count++) {
                Carta carta = new Carta(valor[valor_count], tipo[tipo_count]);
                deck.add(carta);
            }
        }
        System.out.println("Construindo deck:");
        System.out.println(deck);
    }

    public void embaralhar(){
        for (int carta1 = 0; carta1 < deck.size(); carta1++){
            int carta2 = random.nextInt(deck.size());
            Carta cartaAtual = deck.get(carta1);
            Carta cartaEscolhida = deck.get(carta2);
            deck.set(carta1, cartaEscolhida);
            deck.set(carta2, cartaAtual);
        }
        System.out.println("Deck Embaralhado:");
        System.out.println(deck);
    }

    public int reduzirAsJogador(){
        while (somaJogador> 21 && asJogador>0){
            somaJogador -=10;
            asJogador -= 1;
        }
        return somaJogador;
    }
    public int reduzirAsDealer(){
        while (somaDealer> 21 && asDealer>0){
            somaDealer -=10;
            asDealer -= 1;
        }
        return somaDealer;
    }
    private void reiniciar() {
        somaDealer = 0;
        asDealer = 0;
        somaJogador = 0;
        asJogador = 0;
        cartaVirada = null;
        maoDealer.clear();
        maoJogador.clear();

        montarDeck();
        embaralhar();
        iniciar();

        botaoHit.setEnabled(true);
        botaoStand.setEnabled(true);
        botaoReiniciar.setEnabled(false);

        barraJogo.repaint();
    }
}