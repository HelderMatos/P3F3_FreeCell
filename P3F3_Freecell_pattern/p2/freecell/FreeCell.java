package p2.freecell;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class FreeCell extends JFrame {

	private ZonaJogo painel = new ZonaJogo();
	
	private int click = 1;
	
	private static final int NCOLUNAS = 8;
	private static final int NCELULAS = 4;
	private static final int NCASAS = 4;
	
	private JMenuItem repor;
	
	//private Baralho baralho = new Baralho( 73, 97, "cartas.gif", 1 );	
	private Baralho baralho = new Baralho( 73, 97, "cartaswin.gif", 1 );
	ContentorCartas origens[] = new ContentorCartas[ NCOLUNAS + NCELULAS ];
	ContentorCartas destinos[] = new ContentorCartas[ NCOLUNAS + NCELULAS + NCASAS ];
	private ContentorCartas aOrigem;

	public FreeCell( ){
		setTitle( "Freecell" );
		setSize( 680, 600 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		painel.setBackground( new Color( 0, 127, 0 ) );
		getContentPane().add( painel );		
		
		colocarMenus();
		
		// colocar colunas no sitio
		for( int i = 0; i< NCOLUNAS; i++ ){ 
			Coluna c = new Coluna( new Point( 8 + (baralho.getComprimentoCarta()+8)*i, 130 ),
										baralho.getComprimentoCarta(), baralho.getAlturaCarta());
			origens[ i ] = c;
			destinos[ i ] = c;
		}

		// colocar celulas no sitio
		for( int i = 0; i< NCELULAS; i++ ){
			Celula c = new Celula( new Point( 2 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					                     baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );
			origens[ i + NCOLUNAS ] = c;
			destinos[ i + NCOLUNAS ] = c;
		}
		
		// colocar casas no sitio
		for( int i = 0; i < NCASAS; i++ ) {
			Casa c = new Casa( new Point( 360 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					                     baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );			
			destinos[ i + NCOLUNAS + NCELULAS ] = c;
		}

		distribuirCartas();
		
		painel.addMouseListener( new MouseAdapter() {
			public void mousePressed( MouseEvent e ){
				if( e.getButton() != MouseEvent.BUTTON1 )
					return;
				if( click == 1 )
					escolherOrigem( e.getPoint() );
				else {
					escolherDestino( e.getPoint() );
					testarFim();
				}
			}			
		});
	}
	
	
	private void distribuirCartas() {
		baralho.baralhar();
		for( int i=0; i < 52; i++ ) {
			Carta c = baralho.dar( i );
			c.virar();
			// assume que as primeras origens s�o as colunas
			origens[ i % NCOLUNAS ].colocar( c );
		}
	}
	
	
	private class ZonaJogo extends JPanel {
		
		public void paint( Graphics g ){
			super.paint( g );
			for( ContentorCartas cc : destinos )
				cc.desenhar(  g );
		}
	}
	
	private void escolherOrigem( Point pt ) {
		for( int i=0; i < origens.length; i++){
			if( origens[ i ].estaDentro( pt ) ){
				if( origens[ i ].estaVazio() )
					return;
				aOrigem = origens[ i ];
				aOrigem.setSeleccionado( true );
				click = 2;
				repaint();
			}
		}
	}

	
	private void escolherDestino( Point pt ) {
		// volta tudo ao estado inicial
		aOrigem.setSeleccionado( false );
		click = 1;
		
		// ver se se pode mexer a carta
		Carta c = aOrigem.getCarta(); 
		for( int i=0; i < destinos.length; i++){
			if( destinos[ i ].estaDentro( pt ) ){
				if( destinos[ i ].podeReceber( c ) ){
					destinos[ i ].receber( aOrigem.retirar() );
					break;
				}							
			}
		}
		
		repaint();		
	}
	
	private void testarFim() {
		if( ganhou() )
			JOptionPane.showMessageDialog( this, "Parab�ns! Ganhou!",
                                           "Freecell", JOptionPane.INFORMATION_MESSAGE);
		else if( perdeu() )
			JOptionPane.showMessageDialog( this, "J� n�o tem mais jogadas v�lidas!!! Perdeu!",
	                                       "Freecell", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean ganhou(){
		// ver se todas as origens est�o vazias
		for( ContentorCartas cc : origens )
			if( !cc.estaVazio()  )
				return false;
		
		return true;			
	}
	
	private boolean perdeu() {
		// ver todas as combinacoes poss�veis a ver se j� n�o pode jogar
		for( int i= 0; i < origens.length; i++){
			if( origens[i].estaVazio() ) // se a origem est� vazia n�o perdeu de certeza
				return false;
			Carta c = origens[i].getCarta();
			for( int d = 0; d < destinos.length; d++ )
				if( destinos[d].podeReceber( c ))
					return false;
		}
			
		// se ap�s todas as combina��es n�o conseguir mover � porque perdeu 
		return true;
	}


	private void memorizarSituacaoJogo() {
		// memorizar aqui a situa��o de jogo
	}

	private void reporSituacaoJogo() {
		// repor aqui a situa��o de jogo
	}
		
	
	// m�todo que vai colocar os menus que temos de implementar
	private void colocarMenus(){
		JMenuBar bar = new JMenuBar();
		
		JMenu menu = new JMenu( "Jogo" );
		JMenuItem lembrar = new JMenuItem("Memorizar posi��o jogo");
		lembrar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			  memorizarSituacaoJogo();
			}
		});
		menu.add( lembrar );
		repor = new JMenuItem("Repor posi��o jogo");
		repor.setEnabled( false );
		repor.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			  reporSituacaoJogo();
			}
		});
		menu.add( repor );
		bar.add( menu );
		
		setJMenuBar( bar );
	}
	
	
	public static void main(String[] args) {
		FreeCell frame = new FreeCell( );
		frame.setVisible( true );
	}

}
