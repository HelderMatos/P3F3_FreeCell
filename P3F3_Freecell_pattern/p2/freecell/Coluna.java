package p2.freecell;

import java.awt.Point;

public class Coluna extends DefaultContentorCartas {

	public Coluna( Point t, int comp, int alt ){
		super( t, comp, alt );
	}
		
	public boolean podeReceber(Carta c) {
		// se está vazio pode receber qualquer carta
		if( estaVazio() )
			return true;
		
		// se não está vazio tem de receber de cor diferente e consecutiva
		Carta ultima = super.getCarta( );
		return ( ultima.getCor() != c.getCor() ) &&
		       ( ultima.getFace() - 1 == c.getFace() ); 
	}
	
	
	public void colocar( Carta c ){
		if( estaVazio() ){
			c.setPosicao( getPosicao() );			
		}
		else {			
			Carta ultima = super.getCarta( );
		
			Point nova = new Point( ultima.getPosicao().x, ultima.getPosicao().y + ultima.getAltura() / 3 );
			c.setPosicao( nova );
		}		
		super.colocar( c );		
	}

}
