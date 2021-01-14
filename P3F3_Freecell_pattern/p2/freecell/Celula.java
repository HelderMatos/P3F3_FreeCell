package p2.freecell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Celula extends DefaultContentorCartas {

	public Celula(Point t, int comp, int alt) {
		super(t, comp, alt);
	}

	public boolean podeReceber(Carta c) {		
		return estaVazio();
	}
	
	public void colocar( Carta c ){
		c.setPosicao( new Point( getPosicao().x+1, getPosicao().y+1 ) );
		super.colocar( c );
	}
	
	public void desenhar( Graphics g ){
		int x1 = getPosicao().x;
		int y1 = getPosicao().y;
		int x2 = getPosicao().x + getComprimento();
		int y2 = getPosicao().y + getAltura();
		
		g.setColor( Color.black );
		g.drawLine( x1, y1, x2, y1 );
		g.drawLine( x1, y1, x1, y2 );
		g.setColor( Color.green );	
		g.drawLine( x2, y1+1, x2, y2 );
		g.drawLine( x1+1, y2, x2, y2 );			
		
		if( !estaVazio() )
			getCarta().desenhar( g );				
	}


}
