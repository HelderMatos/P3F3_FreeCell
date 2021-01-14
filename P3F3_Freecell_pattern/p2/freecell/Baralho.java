package p2.freecell;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Baralho {
	
	private static final int NUMCARTAS = 52;
	private BufferedImage imgCartas;
	private int comprimentoCarta;
	private int alturaCarta;
	private Carta asCartas[]; // array de cartas
	private int next = 0;     // carta a dar
	
	/** cria um baralho em que as cartas têm um determinado comprimento e largura e a imagem é dada num ficheiro */
	public Baralho( int comp, int alt, String fichCartas, int costas ){		
		comprimentoCarta = comp;
		alturaCarta = alt;
		imgCartas = lerImagem( fichCartas );
		asCartas = new Carta[ NUMCARTAS + 1 ]; // 52 cartas mais o joker
		int i = 0;
		
		// definir a imagem das costas
		BufferedImage costasImg = imgCartas.getSubimage( costas * comprimentoCarta, 9*alturaCarta, comprimentoCarta, alturaCarta );
		
		// criar as 52 cartas
		for( int naipe = Carta.OUROS; naipe <= Carta.ESPADAS; naipe++ ){
			for( int face = Carta.AS; face <= Carta.REI; face++ ){
				asCartas[ i ] = new Carta( face, naipe );
				asCartas[ i ].setTamanho(comp, alt);
				asCartas[ i ].setVistaCima( imgCartas.getSubimage(face*comprimentoCarta, naipe*2*alturaCarta, comprimentoCarta, alturaCarta) );
				asCartas[ i ].setVistaSeleccionada( imgCartas.getSubimage(face*comprimentoCarta, (naipe*2+1)*alturaCarta, comprimentoCarta, alturaCarta) );
				asCartas[ i ].setVistaCostas( costasImg );  
				i++;
			}
		}
		
		// definir o joker
		asCartas[ NUMCARTAS ] = new Carta( Carta.JOKER, Carta.NENHUM );
		asCartas[ NUMCARTAS ].setVistaCima( imgCartas.getSubimage( 0, 8*alturaCarta, comprimentoCarta, alturaCarta) );
		asCartas[ NUMCARTAS ].setVistaCostas( costasImg );
	}

	
	/** retorna a próxima carta. Se não tiver mais retorna null */
	public Carta dar( ){		
		if( next > NUMCARTAS )
			return null;
		next++;
		return asCartas[ next - 1 ];
	}
	
		
	/** retorna a carta com o indice i. Se não tiver mais retorna null */	
	public Carta dar( int i ){
		return asCartas[ i ];
	}
	
	/** indica se tem mais cartas para dar */
	public boolean temMais(){
		return next < NUMCARTAS;			
	}
		
	/** baralha as cartas presentes no baralho */
	public void baralhar( ){
		// se baralhou voltar tudo ao principio
		next = 0; 
		
		Random rand = new Random();
		// baralhar 200 cartas à sorte
		for( int i=0; i< 200; i++ ) {
			int o = rand.nextInt( NUMCARTAS );
			int d = rand.nextInt( NUMCARTAS );
			Carta troca = asCartas[ o ];
			asCartas[ o ] = asCartas[ d ];
			asCartas[ d ] = troca;
		}
	}
	
	
	public int getComprimentoCarta(){
		return comprimentoCarta;		
	}

	
	public int getAlturaCarta(){
		return alturaCarta;		
	}
	
	
    /** função para auxiliar na leitura de um ficheiro de imagem */
    private BufferedImage lerImagem( String nome ){
        try {
            File f = new File(nome);
            return javax.imageio.ImageIO.read(f);
        } catch (java.io.IOException e) {System.out.println("Erro a ler o ficheiro de imagem: " + nome); }
        return null;
    }
}
