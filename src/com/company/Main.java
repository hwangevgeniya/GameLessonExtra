package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Random;

    public class Main {


        public static String[] heroesNames = {"Liu Kang", "Jax", "Kung Lao"};
        public static int[] heroesHealth = {270, 280, 250};
        public static int[] heroesDamage = {20, 15, 25};

        public static String heroMedic = "Medic";
        public static int medicHealth = 300;
        public static int medicDamage = 0;

        public static String heroGolem = "Golem";
        public static int golemHealth = 100;
        public static int golemDamage = 5;

        public static String heroLucky = "Lucky";
        public static int luckyHealth = 150;
        public static int luckyDamage = 0;


        public static String bossName = "Shao Kahn";
        public static int bossHealth = 1500;
        public static int bossDamage = 50;
        public static int roundNumber = 0;
        public static String superDamageHero = "";
        public static String heroForHealing = "";



        public static void main(String[] args) {
            // write your code here

            printStatistics();
            System.out.println("-------Mortal Kombat started!-------");

            while (!isGameFinished()){
                round();
            }
        }

        public static boolean chanceForLucky(){

            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            return randomBoolean;

        }

        public static boolean isGameFinished(){
            if (bossHealth<=0){
                System.out.println("Heroes win! " + "Mortal Kombat is finished!");
                return true;
            }
            boolean allHeroesDead = true;
            for (int heroHealth:heroesHealth) {
                if (heroHealth > 0){
                    allHeroesDead = false;
                    break;
                }
            }
            if(allHeroesDead){
                System.out.println(bossName + " wins! " + "Mortal Kombat is finished!");
            }
            return allHeroesDead;
        }


        public static void round(){
            roundNumber++;
            System.out.println("-------Round " + roundNumber + "-------");
            bossHit();
            superDamageHero = getHeroForDamageBossDefence();

            heroesHit();
            heroForHealing = getHeroForHealing();


                if (chanceForLucky()) {
                    System.out.println("Lucky is safe!");
                } else {
                    luckyHealth = luckyHealth - bossDamage;
                    System.out.println("Lucky is not safe!");
                }

            printStatistics();
        }

        public static void bossHit(){
            for (int i = 0; i < heroesHealth.length; i++) {
                if(heroesHealth[i] > 0 && bossHealth > 0) {

                    if(golemHealth > 0) {
                        heroesHealth[i] = heroesHealth[i] - bossDamage + (bossDamage / 5);
                        golemHealth = golemHealth - ((bossDamage / 5) * (heroesHealth.length-1));
                    }
                    else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                   }
                }
            medicHealth = medicHealth - bossDamage;
        }

        public static void heroesHit(){

            Random random = new Random();
            int coeff = random.nextInt(9) + 2;

            Random randomHeal = new Random();
            int coeffHeal = random.nextInt(50);


            for (int i = 0; i < heroesDamage.length; i++) {
                if(heroesHealth[i] > 0 && bossHealth > 0) {
                    if(superDamageHero == heroesNames[i]){
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                        System.out.println("Super Damage Hero = " + superDamageHero + " " + (heroesDamage[i]*coeff));
                    }

                    else {
                        bossHealth = bossHealth - heroesDamage[i];

                    }
                    if(heroesHealth[i] < 100 && heroesHealth[i] > 0 && bossHealth > 0 && medicHealth > 0){
                        if(heroForHealing == heroesNames[i]) {

                            heroesHealth[i] = heroesHealth[i] + coeffHeal;
                            System.out.println("Medic healed " + heroForHealing + " + " + coeffHeal);

                        }
                    }
                }
                if (heroesHealth[i] < 0){
                    heroesHealth[i] = 0;
                }
                if (bossHealth < 0){
                    bossHealth = 0;
                }
                if (medicHealth < 0){
                    medicHealth = 0;
                }
                if (golemHealth < 0){
                    golemHealth = 0;
                }
                if(luckyHealth < 0){
                    luckyHealth = 0;

                }
            }
        }






        public static String getHeroForDamageBossDefence(){

            Random random = new Random();
            int randomIndex = random.nextInt(heroesNames.length);
            return heroesNames[randomIndex];
        }

        public static String getHeroForHealing(){
            Random random = new Random();
            int randomIndex = random.nextInt(heroesNames.length);
            return heroesNames[randomIndex];
        }

        public static void printStatistics(){

            System.out.println(bossName + " = Health " + bossHealth + " = Damage [" + bossDamage + "]");

            for (int i = 0; i < heroesNames.length; i++) {
                System.out.println(heroesNames[i] + " = Health " + heroesHealth[i] +
                        " = Damage [" + heroesDamage[i] + "]");

            }
            System.out.println(heroMedic + " = Health " + medicHealth + " = Damage [" + medicDamage + "]");
            System.out.println(heroGolem + " = Health " + golemHealth + " = Damage [" + golemDamage + "]");
            System.out.println(heroLucky + " = Health " + luckyHealth + " = Damage [" + luckyDamage + "]");

        }
    }
