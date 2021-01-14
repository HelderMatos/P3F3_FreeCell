package p2.freecell;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public abstract class DefaultContentorCartas implements ContentorCartas {
	
	private Vector<Carta> asCartas = new Vector<Carta>();
	private Point topo;
	private int comprimento;
	private int altura;
	private boolean selecionado = false;
	
	public DefaultContentorCartas( Point t, int comp, int alt ){
		topo = t;
		comprimento = comp;
		altura = alt;
	}
		
	public void colocar( Carta c ){
		asCartas.add( c );
	}	
	
	public boolean receber( Carta c ){
		if( !podeReceber( c ) )
			return false;
		colocar( c );
		return true;
	}
	
	public void desenhar( Graphics g ){
		for( int i=0; i < asCartas.size(); i++ ){
			Carta card = (Carta) asCartas.get( i );
			card.desenhar( g );
		}			
	}
	
	public Carta retirar( ){		
		return (Carta) asCartas.remove( asCartas.size()-1 );
	}

	public Carta getCarta( ){
		return (Carta) asCartas.get( asCartas.size()-1 );
	}
	
	public boolean estaVazio() {
		return asCartas.isEmpty();
	}
	
	public boolean estaDentro( Point pt ) {
		// ver se clicou em alguma das cartas do componente
		for( int i = 0; i < asCartas.size(); i++ )
			if( ((Carta)asCartas.get( i )).estaDentro( pt ) )
				return true;
		
		// se não ver se clicou na área do componente
		return topo.x <= pt.x && topo.y <= pt.y && topo.x + comprimento >= pt.x && topo.y + altura >= pt.y;
	}
	
	public void setSeleccionado( boolean sel ) {
		selecionado = sel; 
		if( !estaVazio() )
			getCarta().setSelecionada( sel );
	}
	
	public boolean getSeleccionado( ){
		return selecionado;
	}
	
	public Point getPosicao() {
		return topo;
	}
	
	public int getComprimento() {
		return comprimento;
	}
	
	public int getAltura(){
		return altura;
	}
	
	public void setposicao( Point p ){
		topo = p;
	}
	
	public void limpar() {
		asCartas.clear();
	}
}
