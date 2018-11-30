package ohtu.intjoukkosovellus;

public class IntJoukko {
    
    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        alustaLista();
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti <= 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti nolla tai alle, taulukkoa ei voi luoda.");
        }
        ljono = new int[kapasiteetti];
        alustaLista();
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;     
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti <= 0 || kasvatuskoko <= 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti tai kasvatuskoko nolla tai alle, taulukkoa ei voi luoda.");
        }
        ljono = new int[kapasiteetti];
        alustaLista();
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;     
    }
    
    public void alustaLista(){
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
    }
    
    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            mahtuu();
            return true;
        }
        return false;
    }
    
    public void mahtuu() {
        if (alkioidenLkm == ljono.length) {
            int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(ljono, uusiTaulukko);
            ljono = uusiTaulukko;
        }
    }
    
    public boolean kuuluu(int luku) {
        for (Integer i : ljono) {
            if (i == luku) {
                return true;
            }
        }
        return false;
        
    }
    
    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                ljono[i] = 0;
                korjaaJononUlkoasuPoistettaessaKohdasta(i);                
                return true;
            }
        }
        return false;
    }

    public void korjaaJononUlkoasuPoistettaessaKohdasta(int luku) {
        for (int j = luku; j < alkioidenLkm - 1; j++) {
            ljono[j] = ljono[j + 1];
            
        }
        alkioidenLkm--;
    }
    
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
        
    }
    
    public int mahtavuus() {
        return alkioidenLkm;
    }
    
    @Override
    public String toString() {
        String tuotos = "{";
        if (alkioidenLkm == 0) {
            return tuotos + "}";
        } else if (alkioidenLkm == 1) {
            return tuotos + ljono[0] + "}";
        } else {            
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i] +", ";               
            }
            return tuotos + ljono[alkioidenLkm - 1] + "}";
        }
    }
    
    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
    
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko uusi = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            uusi.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            uusi.lisaa(bTaulu[i]);
        }
        return uusi;
    }
    
    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko uusi = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    uusi.lisaa(bTaulu[j]);
                }
            }
        }
        return uusi;
        
    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko uusi = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            uusi.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            uusi.poista(i);
        }
        
        return uusi;
    }
    
}
