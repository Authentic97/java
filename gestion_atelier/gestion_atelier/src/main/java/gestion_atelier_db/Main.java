package gestion_atelier_db;
import java.util.Scanner;

import gestion_atelier_db.entities.Categorie;
import gestion_atelier_db.repositories.ITables;
import gestion_atelier_db.repositories.bd.CategorieRepository;
import gestion_atelier_db.repositories.list.TableCategories;
import gestion_atelier_db.services.CategorieService;
import gestion_atelier_db.services.CategorieServiceImpl;

public class Main {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        ITables<Categorie> repository=new CategorieRepository();
        CategorieService categorieServiceImpl=new CategorieServiceImpl(repository);
        int choix;
        do {
            
            System.out.println("-------MENU GENERAL-------");
            System.out.println("1----Ajouter categorie");
            System.out.println("2----Lister les categories");
            System.out.println("3----Quitter");
            choix=scanner.nextInt();
            scanner.nextLine();
            switch(choix){
                case 1:
                    System.out.println("Entrer le libelle :");
                    Categorie categorie=new Categorie(scanner.nextLine());
                    categorieServiceImpl.add(categorie);
                    break;
                case 2:
                    categorieServiceImpl.getAll().forEach(System.out::println);
                    break;
                
                case 3:
                    break;
                        
                default:
                    System.exit(3);
                break;

            }
        } while (choix!=3);
        
    }

    public static void effacer(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } 
}
       