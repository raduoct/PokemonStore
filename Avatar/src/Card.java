import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

public class Card {
    public static void main(String[] args) {
        BufferedReader f = null;
        try {
            // Deschidem fisierul
            f = new BufferedReader(new FileReader("shop.txt"));
            // Citim si afisam fisierul caracter cu caracter
            Shop PokemonShop = null;
            String line = f.readLine();
            while ( line != null) {
                String tokens[] = line.split(";");
                Pokemon newPk = null;
                if(tokens[0].equals("Fire")){
                    newPk = new FirePk(tokens[1],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[2]));
                }
                else if(tokens[0].equals("Air")){
                    newPk = new AirPk(tokens[1],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[2]));
                }
                else if(tokens[0].equals("Earth")){
                    newPk = new EarthPk(tokens[1],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[2]));
                }
                else if(tokens[0].equals("Water")){
                    newPk = new WaterPk(tokens[1],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[2]));
                }
                for(int i = 4; i < tokens.length; i+=4){
                    newPk.addAttack(new Attack(tokens[i],tokens[i+3],Integer.parseInt(tokens[i+1]),Integer.parseInt(tokens[i+1])));
                }
                PokemonShop.addPokemon(newPk);
                line = f.readLine();
            }
            BufferedReader g = null;
            try {
                // Deschidem fisierul
                g = new BufferedReader(new FileReader("wishlist.txt"));
                // Citim si afisam fisierul caracter cu caracter
                ShopingList wishlist = null;
                Shop myCollection = null;
                line = g.readLine();
                String tokens[] = line.split(";");
                wishlist.setSumofaccount(Integer.parseInt(tokens[0]));
                line = g.readLine();
                while ( line != null) {
                    tokens = line.split(";");
                    for(String token : tokens){
                        wishlist.addPokemon(token,PokemonShop);
                    }
                }


            }
            catch (FileNotFoundException e) {
                //Tratam un tip de exceptie
                System.err.println("File not found");
            }
            catch (IOException e) {
                //Tratam alt tip de exceptie
                System.out.println("Input error");
                e.printStackTrace();
            }


        }
        catch (FileNotFoundException e) {
            //Tratam un tip de exceptie
            System.err.println("File not found");
        }
        catch (IOException e) {
            //Tratam alt tip de exceptie
            System.out.println("Input error");
            e.printStackTrace();
        }


    }
}
class Attack{
    private String name;
    private String description;
    private int damage;
    private int energy;
    public Attack(String name, String description, int damage, int energy){
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.energy = energy;
    }
    String getName(){
        return name;
    }
    void setName(String name){
        this.name = name;
    }
    String getDescription(){
        return description;
    }
    void setDescription(String description){
        this.description = description;
    }
    int getDamage(){
        return damage;
    }
    void setDamage(int damage){
        this.damage = damage;
    }
    int getEnergy(){
        return energy;
    }
    void setEnergy(int energy){
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", damage=" + damage +
                ", energy=" + energy +
                '}';
    }
}
class Pokemon{
    private String name;
    private Vector<Attack> attacks;
    private String weakness;
    private int hp;
    private int price;
    public Pokemon(String name, int hp, int price){
        this.name = name;
        this.hp = hp;
        this.price = price;
        attacks = new Vector<>();
    }
    String getName(){
        return name;
    }
    void setName(String name){
        this.name = name;
    }
    String getWeakness(){
        return weakness;
    }
    void setWeakness(String weakness){
        this.weakness = weakness;
    }
    int getHp(){
        return hp;
    }
    void setHp(int hp){
        this.hp = hp;
    }
    int getPrice(){
        return price;
    }
    void setPrice(int price){
        this.price = price;
    }
    void addAttack(Attack newAtk){
        attacks.add(newAtk);
    }
    void listAttacks(){
        for(Attack a : attacks){
            System.out.println(a.toString());
        }
    }
    @Override
    public String toString() {
        return "Pokemon{" +
                "type= " + getClass() + " " +
                "name='" + name + '\'' +
                ", weakness='" + weakness + '\'' +
                ", hp=" + hp +
                '}';
    }
}
class FirePk extends Pokemon{
    public FirePk(String name,int hp, int price){
        super(name,hp,price);
        setWeakness("water");
    }
}
class WaterPk extends Pokemon{
    public WaterPk(String name,int hp,int price){
        super(name,hp,price);
        setWeakness("earth");
    }
}
class EarthPk extends Pokemon{
    public EarthPk(String name,int hp,int price){
        super(name,hp,price);
        setWeakness("air");
    }
}
class AirPk extends Pokemon{
    public AirPk(String name,int hp,int price){
        super(name,hp,price);
        setWeakness("fire");
    }
}
class Shop{
    private Vector<Pokemon> pokemons;
    public Shop(){
        pokemons = new Vector<>();
    }
    public Vector<Pokemon> sortby(String criteria){
        if(criteria.equals("price"))
            return null;
        if(criteria.equals("name"))
            return null;
        return null;
    }
    public Vector<Pokemon> typeSort(String type){
        Vector<Pokemon> newVect = new Vector<>();
        if(type.equals("fire"))
            for(Pokemon p : pokemons){
                if(p instanceof FirePk)
                    newVect.add(p);
            }
        else if(type.equals("water"))
            for(Pokemon p : pokemons){
                if(p instanceof WaterPk)
                    newVect.add(p);
            }
        else if(type.equals("earth"))
            for(Pokemon p : pokemons){
                if(p instanceof EarthPk)
                    newVect.add(p);
            }
        else if(type.equals("air"))
            for(Pokemon p : pokemons){
                if(p instanceof AirPk)
                    newVect.add(p);
            }
        newVect.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return newVect;
    }
    public void priceSort(){
        pokemons.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o1.getPrice()-o2.getPrice();
            }
        });

    }
    public void nameSort(){
        pokemons.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
    public Pokemon getPokemon(Pokemon p){
        return pokemons.get(pokemons.indexOf(p));
    }
    public Pokemon getPokemon(String name){
        for(Pokemon p : pokemons){
            if(p.getName().equals(name))
                return p;
        }
        return null;
    }
    public void addPokemon(Pokemon p){
        pokemons.add(p);
    }
    public boolean deletePokemon(Pokemon p){
        return pokemons.remove(p);
    }
    public void listAll(){
        for(Pokemon p : pokemons){
            System.out.println(p.toString());
            p.listAttacks();
        }
    }

}
class ShopingList {
    private int sumofaccount;
    private Vector<Pokemon> pokemons;
    public ShopingList(int sumofaccount){
        this.sumofaccount = sumofaccount;
        pokemons = new Vector<>();
    }
    public int getSumofaccount(){
        return sumofaccount;
    }
    public void setSumofaccount(int sumofaccount){
        this.sumofaccount = sumofaccount;
    }
    public int getTotal(){
        int total = 0;
        for(Pokemon p : pokemons){
            total += p.getPrice();
        }
        return total;
    }
    public void addPokemon(Pokemon p){
        pokemons.add(p);
    }
    public void addPokemon(String name, Shop myShop){
        pokemons.add(myShop.getPokemon(name));
    }
    public boolean removePk(Pokemon p){
        return pokemons.remove(p);
    }
    public void removeAllPK(){
        pokemons.removeAllElements();
    }
    public boolean purchase(){
        if(getTotal() <= sumofaccount){
            sumofaccount -= getTotal();
            removeAllPK();
            return true;
        }
        return false;
    }

}
