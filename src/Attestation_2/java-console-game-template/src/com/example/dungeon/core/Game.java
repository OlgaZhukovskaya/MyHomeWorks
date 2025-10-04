package com.example.dungeon.core;

import com.example.dungeon.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    private final GameState state = new GameState();
    private final Map<String, Command> commands = new LinkedHashMap<>();

    static {
        WorldInfo.touch("Game");
    }

    public Game() {
        registerCommands();
        bootstrapWorld();
    }

    private void registerCommands() {
        commands.put("help", (ctx, a) -> System.out.println("Команды: " + String.join(", ", commands.keySet())));
        commands.put("gc-stats", (ctx, a) -> {
            Runtime rt = Runtime.getRuntime();
            long free = rt.freeMemory(), total = rt.totalMemory(), used = total - free;
            System.out.println("Память: used=" + used + " free=" + free + " total=" + total);
        });
        commands.put("look", (ctx, a) -> System.out.println(ctx.getCurrent().describe()));
        commands.put("move", (ctx, a) -> {

            // ожидаем что у нас на входе должен быть
            // только один аргумент и этот аргумент в итоге
            // существующее направление
            if (a.size() == 0) {

                // если это не так выкидываем ошибку
                // как исключение
                throw new InvalidCommandException("команда move ожидает ровно один аргумент: направление");
            }

            // берём текущую room и пытаемся найти есть ли среди neigbors
            // запрошенная в аргументах направление, реализуем этот метод
            // внутри класса room
            String dir = String.join(" ", a);
            Room current = ctx.getCurrent();
            Room next = current.getNeighborByDirection(dir);
            if (next == null) {

                 // запрошенной комнаты нету
                 throw new InvalidCommandException("запрошенное направление '" + dir + "' не существует в текущей комнате '" + current.getName() + "'");
            }

            // комната по запрошенному направлению существует, перемещаемся в неё:
            // меняем контексты игры и устанавливаем комнату полученную
            // предыдущим методом
            state.setCurrent(next);

            // напишем игроку что было сделано
            System.out.println("Вы переместились из комнаты '" + current.getName() + "' по направлению '" + dir +"' в комнату '" + next.getName() + "'");

            // расскажем про комнату в которую он переместился
            System.out.println(next.describe()); 
        });
        commands.put("take", (ctx, a) -> {

            // опять ожидаем что у нас на входе должно что-то быть
            if (a.size() == 0) {

                // если это не так выкидываем ошибку
                // как исключение
                throw new InvalidCommandException("команда take ожидает ровно один аргумент: предмет");
            }

            // из текущей комнаты забираем предмет и
            // добавляем его в инвентарь игрока
            Room current = ctx.getCurrent();

            // мы тут предполагаем что аргументы разделены пробелами
            // непонятно что делать если это не так, в аргументах
            // они уже в списке и неясно чем они "были" разделены
            // изначально
            String name = String.join(" ", a);

            Item item = current.getItem(name);
            if (item == null) {

                 // запрошенного предмета нету
                 throw new InvalidCommandException("запрошенного предмета '" + name + "' не существует в текущей комнате '" + current.getName() + "'");
            }

            // поместить в инвентарь игрока
            Player player = state.getPlayer();
            player.getInventory().add(item);

            // расскажем что предмет переместился из комнаты в инвентарь
            System.out.println("Предмет '" + item.getName() + "' взят и помещён в инвентарь");

            // покажем весь интвентарь
            System.out.println(player.describeInventory());
        });

        commands.put("inventory", (ctx, a) -> {

            // по каким-то причинам у нас нет объекта inventory
            // поэтому операции с ним, например показать содержимое
            // будем реализовывть в player
            Player player = state.getPlayer();

            // инвентарь группируем по типу item'ов, 
            // сортируем по названию внутри группы
            System.out.println(player.describeInventory());

        });
        commands.put("use", (ctx, a) -> {

            // тут бы сделать проверку получше так как бывают
            // предметы из нескольких слов  
            if (a.size() == 0) {
                throw new InvalidCommandException("команда use ожидает ровно один аргумент: название предмета");
            }
      
            String name = String.join(" ", a);
            Item item = state.getPlayer().getItem(name);
            if (item == null) {
                 // запрошенного предмета нету у игрока в инвентаре
                 throw new InvalidCommandException("запрошенного предмета '" + name + "' не существует в инвентаре");
            }

            // предмет найден, его нужно применить
            item.apply(ctx);
        });
        commands.put("fight", (ctx, a) -> {
            
            // аргументов у команды нет, предполагам что в текущей
            // комнате если есть монстр то бой с ним, если нет, то
            // выходим с тем что монстра нет
            if (a.size() > 0) {
                throw new InvalidCommandException("команда fight не ожидает никаких аргументов");
            }
             
            // смотрим есть ли в комнате монстр
            Room current = ctx.getCurrent();
            Monster monster = current.getMonster();
            if (monster == null) {
                 throw new InvalidCommandException("в текущей комнате '" + current.getName() + "' нет монстра");
            }

            // у нас есть player и monster, две фазы:
            // player бъет монстра, а затем монстр
            // отвечает (если у него hp > 0)
            Player player = state.getPlayer();

            int attack = player.getAttack(); 
	    StringBuilder sb = new StringBuilder("Вы бьете монстра '" + monster.getName() + "' на '" + attack + "'.");

            int mhp = monster.getHp() - player.getAttack();
            if (mhp <= 0) {
                   // монстр погиб, мы должны сообщить игроку
                   // удалить монстра из команты и добавить
                   // очков в scores игроку
                   current.setMonster(null);

                   // например мы добавим количество уровня
                   // погибшего монстра
                   ctx.addScore(monster.getLevel());

                   // сообщаем печальную весть
                   sb.append(" Монстр погиб.");
   		   System.out.println(sb.toString());
                   return;
            }
            sb.append(" HP Монстра: '" + mhp + '"');

            // монстр жив, его hp измнилось но он может 
            // ответить с силой своего уровня
            monster.setHp(mhp); 

            sb.append("\n").append("Монстр '" + monster.getName() + "' отвечает на '" + monster.getLevel() + "'.");
            int chp = player.getHp() - monster.getLevel();
            if (chp <= 0) {
                  // игрок погиб непонятно что тут делать
                  // в целом можно просто сообщить, ну и 
                  // удалить игрока из игры :), если зачем
                  // то мы еще будем какую-то логику
                  // реализовывать
                  ctx.setPlayer(null);

                  sb.append("Вы погибли.");
                  System.out.println(sb.toString());
                  System.exit(0);
            }
            sb.append(" Ваше HP: '" + chp + '"');
            player.setHp(chp);

            System.out.println(sb.toString());
        });
        commands.put("save", (ctx, a) -> SaveLoad.save(ctx));
        commands.put("load", (ctx, a) -> SaveLoad.load(ctx));
        commands.put("scores", (ctx, a) -> SaveLoad.printScores());
        commands.put("exit", (ctx, a) -> {
            System.out.println("Пока!");
            System.exit(0);
        });
    }

    private void bootstrapWorld() {
        Player hero = new Player("Герой", 20, 5);
        state.setPlayer(hero);

        Room square = new Room("Площадь", "Каменная площадь с фонтаном.");
        Room forest = new Room("Лес", "Шелест листвы и птичий щебет.");
        Room cave = new Room("Пещера", "Темно и сыро.");
        square.getNeighbors().put("north", forest);
        forest.getNeighbors().put("south", square);
        forest.getNeighbors().put("east", cave);
        cave.getNeighbors().put("west", forest);

        forest.getItems().add(new Potion("Малое зелье", 5));
        forest.getItems().add(new Potion("Малое зелье", 5));

        forest.getItems().add(new Weapon("Булава дракона", 10));
        forest.getItems().add(new Weapon("Кольцо всевластия", 11));

        forest.setMonster(new Monster("Волк", 1, 8));

        state.setCurrent(square);
    }

    public void run() {

        /*

        // задание 5. раздел 4: ошибка выполнения (пример ArithmeticException) 
        // и перехват исключения
        int n = 1;
        int k = 0;
        try {
            double result = n/k;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // задание 5. раздел 4: этот код компилироваться на будет.
        int n = n;
        */  

        System.out.println("DungeonMini (TEMPLATE). 'help' — команды.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("> ");
                String line = in.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;
                List<String> parts = Arrays.asList(line.split("\\s+"));
                String cmd = parts.get(0).toLowerCase(Locale.ROOT);
                List<String> args = parts.subList(1, parts.size());
                Command c = commands.get(cmd);
                try {
                    if (c == null) throw new InvalidCommandException("Неизвестная команда: " + cmd);
                    c.execute(state, args);
                    state.addScore(1);
                } catch (InvalidCommandException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Непредвиденная ошибка: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }
}
