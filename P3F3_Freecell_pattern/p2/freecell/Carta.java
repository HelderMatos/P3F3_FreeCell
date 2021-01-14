package p2.freecell;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Carta implements Cloneable {
	
	// constantes para os naipes
	public static final int OUROS = 0;
	public static final int COPAS = 1;	
	public static final int PAUS = 2;
	public static final int ESPADAS = 3;
	public static final int NENHUM = 4; // caso do joker

	// constantes para os valores
	public static final int AS = 0;
	public static final int DUQUE = 1;
	public static final int TERNO = 2;
	public static final int QUADRA = 3;
	public static final int QUINA = 4;
	public static final int SENA = 5;
	public static final int SETE = 6;
	public static final int OITO = 7;
	public static final int NOVE = 8;
	public static final int DEZ = 9;
	public static final int VALETE = 10;
	public static final int DAMA = 11;
	public static final int REI = 12;
	
	public static final int JOKER = 13;
	
	// constantes para as cores dos naipes
	public static final int VERMELHA = 0;
	public static final int PRETA = 1;
	
	
	private int face;
	private int naipe;
	private boolean faceUp = false;
	private boolean select = false;
	private Point topo = new Point( 0,0 );
	private int comprimento = 71;
	private int altura = 96;
	
	private BufferedImage vistaCima;
	private BufferedImage vistaCostas;
	private BufferedImage vistaSeleccionada;
		
    public Carta( int fc, int np ) {
    	face = fc;
    	naipe = np;
    }
    
    public Carta clone(){
    	try {
			Carta c = (Carta) super.clone();
			c.topo = new Point( topo.x, topo.y);
			return c;
		} catch (CloneNotSupportedException e) {
			return null;
		}
    }
    
    public void virar( ) {
    	faceUp = !faceUp;
    }
    
    
    public void setFaceUp( boolean vir ){
    	faceUp = vir;
    }
    
    
    public boolean getFaceUp( ){
    	return faceUp;
    }
    
    
    // selecciona ou desselecciona a carta
    public void  setSelecionada( boolean s ) {  
    	select = s;
    }         
    
    
    public boolean getSeleccionada( ) {
    	return select;
    }

    
    //  devolve o valor da carta
    public int getFace( ) {
    	return face;
    }           
    
    //  devolve o naipe da carta
    public int getNaipe( ) {
    	return naipe;
    }
    
    public int getCor( ) {
    	if( naipe == COPAS || naipe == OUROS )
    		return VERMELHA;
    	else 
    		return PRETA;
    }
    
    
    public void setPosicao( Point p ){
    	topo = p;
    }
    
    
    public Point getPosicao( ){
    	return topo;
    }
    
    
    public void setTamanho( int comp, int alt ){
    	comprimento = comp;
    	altura = alt;
    }
    
    
    public int getComprimento( ){
    	return comprimento;
    }
    
    
    public int getAltura( ) {
    	return altura;
    }
    
    
    public void setVistaCima( BufferedImage img ){
    	vistaCima = img;
    }
    
    public BufferedImage getVistaCima(  ){
    	return vistaCima;
    }
    
    
    public void setVistaCostas( BufferedImage img ){
    	vistaCostas = img;
    }

    
    public BufferedImage getVistaCostas(  ){
    	return vistaCostas;
    }
    
    
    public void setVistaSeleccionada( BufferedImage img ){
    	vistaSeleccionada = img;
    }

    public BufferedImage getVistaSeleccionada(  ){
    	return vistaSeleccionada;
    }
    
    
    public boolean estaDentro( Point pt ) {
    	 // para estar dentro a coordenada deverá estar entre o x e x+comp e y e y+comp da carta
    	 return ( ( pt.x >= topo.x && pt.x <= topo.x + comprimento ) && 
    	          ( pt.y >= topo.y && pt.y <= topo.y + altura      )  );
    }
    
    
    // desenha a carta no sistema gráfico representado por g
    public void  desenhar( Graphics g ){
    	if( faceUp ) {
    		if( select )
    			g.drawImage( vistaSeleccionada, topo.x, topo.y, null );
    		else    	
    			g.drawImage( vistaCima, topo.x, topo.y, null );
    	}    		
    	else
    		g.drawImage( vistaCostas, topo.x, topo.y, null );
    }
    

}
