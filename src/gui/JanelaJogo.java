package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.Ambiente;
import project.Cacador;
import project.Dica;
import project.Jogo;
import project.OuvinteJogo;

public class JanelaJogo extends JFrame implements OuvinteJogo, ActionListener{
    private Jogo jogo;
    private boolean encerrado;
    private final Map<String, Posicao> posicoes;
    private final PainelMapa painelMapa;
    private final AnimadorJogador animador;
    private final JLabel rotuloTentativas;
    private final JLabel rotuloChave;
    private final JTextArea areaDicas;
    private final JTextArea areaMensagens;
    private final JTextField campoComando;

    public JanelaJogo(Mapa mapa, Map<String, Posicao> posicoes, Posicao inicio){
        super("World of Zuul");
        this.posicoes = posicoes;
        Jogador jogador = new Jogador(inicio);
        painelMapa = new PainelMapa(mapa, jogador);
        animador = new AnimadorJogador(jogador, new BuscaAEstrela(mapa), painelMapa, this);

        rotuloTentativas = criarRotuloValor();
        rotuloChave = criarRotuloValor();
        areaDicas = new JTextArea(10, 16);
        areaMensagens = new JTextArea(10, 60);
        campoComando = new JTextField();

        setLayout(new BorderLayout());
        add(criarPainelStatus(), BorderLayout.WEST);
        add(painelMapa, BorderLayout.CENTER);
        add(criarPainelDicas(), BorderLayout.EAST);
        add(criarPainelComandos(), BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void conectar(Jogo jogo){
        this.jogo = jogo;
        campoComando.requestFocusInWindow();
    }

    private JLabel criarRotuloValor(){
        JLabel rotulo = new JLabel("-", SwingConstants.CENTER);
        rotulo.setFont(new Font("SansSerif", Font.PLAIN, 28));
        return rotulo;
    }

    private JPanel criarPainelStatus(){
        JPanel painel = new JPanel(new GridLayout(4, 1));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.add(new JLabel("<html><center><b>Nº de Tentativas<br>Restantes:</b></center></html>", SwingConstants.CENTER));
        painel.add(rotuloTentativas);
        painel.add(new JLabel("<html><center><b>Durabilidade da<br>Chave Mestra:</b></center></html>", SwingConstants.CENTER));
        painel.add(rotuloChave);
        return painel;
    }

    private JPanel criarPainelDicas(){
        areaDicas.setEditable(false);
        areaDicas.setLineWrap(true);
        areaDicas.setWrapStyleWord(true);
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.add(new JLabel("<html><b>Dicas Encontradas:</b></html>", SwingConstants.CENTER), BorderLayout.NORTH);
        painel.add(areaDicas, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelComandos(){
        areaMensagens.setEditable(false);
        areaMensagens.setLineWrap(true);
        areaMensagens.setWrapStyleWord(true);
        campoComando.addActionListener(this);

        JPanel linhaComando = new JPanel(new BorderLayout());
        linhaComando.add(new JLabel(" > "), BorderLayout.WEST);
        linhaComando.add(campoComando, BorderLayout.CENTER);

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new JScrollPane(areaMensagens), BorderLayout.CENTER);
        painel.add(linhaComando, BorderLayout.SOUTH);
        return painel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String linha = campoComando.getText().trim();
        campoComando.setText("");
        if(!linha.isEmpty() && jogo != null){
            jogo.executarComando(linha);
        }
    }

    @Override 
    public void exibirMensagem(String texto){
        areaMensagens.append(texto + "\n");
        areaMensagens.setCaretPosition(areaMensagens.getDocument().getLength());
    }

    @Override
    public void cacadorMoveu(Ambiente origem, Ambiente destino){
        Posicao alvo = posicoes.get(destino.getNome());
        if(alvo != null){
            campoComando.setEnabled(false);
            animador.animar(alvo);
        }
    }

    @Override
    public void estadoAtualizado(Cacador cacador){
        rotuloTentativas.setText(String.valueOf(cacador.getTentativas()));
        if(cacador.temChave()){
            rotuloChave.setText(String.valueOf(cacador.getDurabilidadeChave()));
        }else{
            rotuloChave.setText("-");
        }
        StringBuilder texto = new StringBuilder();
        for(Dica dica : cacador.getDicas()){
            texto.append(dica.getTexto()).append("\n\n");
        }
        areaDicas.setText(texto.toString());
    }

    @Override
    public void jogoEncerrado(){
        encerrado = true;
        campoComando.setEnabled(false);
    }

    public void animacaoConcluida(){
        if(!encerrado){
            campoComando.setEnabled(true);
            campoComando.requestFocusInWindow();
        }
    }
}
