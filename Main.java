import java.io.*;
import java.util.*;

/**
 * A class that defines the positions of the cells
 */
class Position{
    private int x, y;

    /**
     * Default constructor
     */
    public Position(){
        this.x = -1;
        this.y = -1;
    }

    /**
     * a constructor that takes in 2 values, and assigns them to the attributes
     * @param x the position on the x-axis
     * @param y the position on the y-axis
     */

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * a constuctor that takes in 1 value, which is another Position object, and assigns its values to the attributes
     * @param other the other Position object
     */
    public Position(Position other){
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     * x value getter
     * @return position on the x-axis
     */
    public int getX(){
        return this.x;
    }

    /**
     * y value getter
     * @return position on the y-axis
     */
    public int getY(){
        return this.y;
    }

    /**
     * a setter for positions
     * @param a positino on the x-axis
     * @param b positoin on the y-axis
     */
    public void setPos(int a, int b){
        this.x = a;
        this.y = b;
    }

    /**
     * a method to print the values of this Position object
     */
    public void print(){
        System.out.println(x+" "+y);
    }

    /**
     * a comperator that checks if this Position object has the same position as another Position object
     * @param other the other position object
     * @return true if they are equal, false otherwise
     */
    public Boolean equals (Position other){
        return (x == other.getX() && y == other.getY());
    }
}

/**
 * A class that defines each cell of the map
 */

class Cell{
    private Boolean dangerous, tortuga, rock, kraken, davy, jack, chest;

    /**
     * Default constructor
     */
    public Cell(){
        this.dangerous = false;
        this.tortuga = false;
        this.rock = false;
        this.kraken = false;
        this.davy = false;
        this.jack = false;
        this.chest = false;
    }

    /**
     * a constructor that takes in a list of stats and sets them to the attributes
     * @param stats the list of stats representing the attribue
     */
    public Cell(Boolean stats[]){
        this.dangerous = stats[0];
        this.tortuga = stats[1];
        this.rock = stats[2];
        this.kraken = stats[3];
        this.davy = stats[4];
        this.jack = stats[5];
        this.chest = stats[6];
    }

    /**
     * stats getter
     * @return the stats (a list of the attributes)
     */
    public Boolean[] getStats(){
        Boolean[] ret= {dangerous, tortuga, rock, kraken, davy, jack, chest};
        return ret;
    }

    /**
     * checks if this cell has Jack in it
     * @return true if it does, false otherwise
     */
    public Boolean isJack() {
        return this.jack;
    }

    /**
     * checks if this cell is dangerous
     * @return true if it does, false otherwise
     */
    public Boolean isDangerous(){
        return this.dangerous;
    }

    /**
     * checks if this cell has Tortuga in it
     * @return true if it does, false otherwise
     */
    public Boolean isTortuga(){
        return this.tortuga;
    }

    /**
     * checks if this cell has the rock in it
     * @return true if it does, false otherwise
     */
    public Boolean isRock(){
        return this.rock;
    }

    /**
     * checks if this cell has Kraken in it
     * @return true if it does, false otherwise
     */
    public Boolean isKraken(){
        return this.kraken;
    }

    /**
     * checks if this cell has Davy Jones in it
     * @return true if it does, false otherwise
     */
    public Boolean isDavy(){
        return this.davy;
    }

    /**
     * checks if this cell has Dead Man's Chest in it
     * @return true if it does, false otherwise
     */
    public Boolean isChest(){
        return this.chest;
    }

    /**
     * sets the dangerous attribute
     * @param dangerous the desired value of dangerous
     */
    public void setDangerous(Boolean dangerous){
        this.dangerous = dangerous;
    }

    /**
     * sets the kraken attribute
     * @param kraken the desired value of kraken
     */
    public void setKraken(Boolean kraken){
        this.kraken = kraken;
    }

    /**
     * sets the jack attribute
     * @param jack the desired value of jack
     */
    public void setJack(Boolean jack){
        this.jack = jack;
    }

    /**
     * sets the rock attribute
     * @param rock the desired value of rock
     */
    public void setRock(Boolean rock){
        this.rock = rock;
    }

    /**
     * sets the tortuga attribute
     * @param tortuga the desired value of tortuga
     */
    public void setTortuga(Boolean tortuga){
        this.tortuga = tortuga;
    }

    /**
     * sets the davy attribute
     * @param davy the desired value of davy
     */
    public void setDavy(Boolean davy){
        this.davy = davy;
    }

    /**
     * sets the chest attribute
     * @param chest the desired value of chest
     */
    public void setChest(Boolean chest){
        this.chest = chest;
    }

}

/**
 * A class that helps in the A* and Backtrack algorithms
 */
class Node implements Comparable<Node>{
    private Position cell;
    private Position path[];
    private int pathLength;
    private int val;

    /**
     * Default contructor
     */
    public Node (){
    }

    /**
     * A constuctor that takes in a cell position and sets it to the attribute cell, also initializes the path array
     * @param cell a position
     */
    public Node (Position cell){
        pathLength = 0;
        val = 0;
        this.cell = new Position(cell);
        path = new Position[81];
        for (int i=0; i<81; i++)
        {
            path[i] = new Position();
        }
    }

    /**
     * path length getter
     * @return the length of the path
     */
    public int getPathLength(){
        return pathLength;
    }

    /**
     * path array getter
     * @return the path array
     */
    public Position[] getPath (){
        return path;
    }

    /**
     * cell setter
     * @param cell the desired value of cell
     */
    public void setCell(Position cell){
        this.cell= new Position(cell);
    }

    /**
     * cell getter
     * @return the cell (Position)
     */
    public Position getCell(){
        return cell;
    }

    /**
     * value getter (the value represents different things in each algorithm)
     * @return the val
     */
    public int getVal (){
        return val;
    }

    /**
     * value setter
     * @param val the desiered value of val
     */
    public void setVal(int val){
        this.val = val;
    }

    /**
     * A method that adds a position to the path array
     * @param newPosition the position to be added
     */
    public void addPosition (Position newPosition){
        path[pathLength] = new Position(newPosition);
        pathLength += 1;
    }

    /**
     * A method to pop the last positiono from the path array
     * @return the popped position
     */
    public Position popPath (){
        if (pathLength >=0)
        {
            pathLength -= 1;
            return new Position(path[pathLength]);
        }
        return new Position();
    }

    /**
     * A method that prints the values of the positions inside the path array
     */
    public void printPath (){
        for (int i=0;i<pathLength;i++)
        {
            path[i].print();
        }
    }

    // And overridden method so I can use TreeSet<Node>
    @Override
    public int compareTo(Node o) {
        if (this.val < o.getVal())
            return -1;
        else if (this.val > o.getVal())
            return 1;
        return 0;
    }
}

/**
 * A class that defines the sea (The Caribbean), and gives the actor (Jack) information about the environment WITHOUT exploiting any data.
 * (The fullMap[][] saves the entire map, the map[][] represents what Jack has discoverd of the sea so far, and he cannot do any action to access fullMap[][])
 */
class Sea{
    private Cell map[][];
    private Cell fullMap[][];
    private int variant;
    private Position jackPos, kraken;

    /**
     * Default contructor
     */
    public Sea (){}

    /**
     * A constructor that takes in the variant and generates a random map (Called when the user only provieds a variant)
     * where fullMap represents the whole map
     * and map represents the map discovered by Jack
     * @param variant the variant
     */
    public Sea (int variant){
        this.variant = variant;
        map = new Cell[9][9];
        for (int i=0; i<9; i++)
        {
            for (int j=0;j<9;j++)
            {
                map[i][j] = new Cell();
            }
        }
        fullMap = new Cell[9][9];
        for (int i=0; i<9; i++)
        {
            for (int j=0;j<9;j++)
            {
                fullMap[i][j] = new Cell();
            }
        }
        createRandomMap();
    }

    /**
     * A constructor that takes in the positions of everything and builds the map according to them. (Called when the user provides input)
     * where fullMap represents the whole map
     * and map represents the map discovered by Jack
     * @param variant the variant
     * @param jackPosition the position of Jack
     * @param davyPosition the position of Davy Jones
     * @param krakenPosition the position of the Kraken
     * @param rockPosition the position of the Rock
     * @param chestPosition the positon of Dead Man's Chest
     * @param tortugaPosition the position of Tortuga
     */
    public Sea (int variant, Position jackPosition, Position davyPosition, Position krakenPosition, Position rockPosition, Position chestPosition, Position tortugaPosition){
        this.variant = variant;
        map = new Cell[9][9];
        for (int i=0; i<9; i++)
        {
            for (int j=0;j<9;j++)
            {
                map[i][j] = new Cell();
            }
        }
        fullMap = new Cell[9][9];
        for (int i=0; i<9; i++)
        {
            for (int j=0;j<9;j++)
            {
                fullMap[i][j] = new Cell();
            }
        }
        fullMap[jackPosition.getX()][jackPosition.getY()].setJack(true);
        fullMap[davyPosition.getX()][davyPosition.getY()].setDavy(true);
        fullMap[krakenPosition.getX()][krakenPosition.getY()].setKraken(true);
        fullMap[rockPosition.getX()][rockPosition.getY()].setRock(true);
        fullMap[chestPosition.getX()][chestPosition.getY()].setChest(true);
        fullMap[tortugaPosition.getX()][tortugaPosition.getY()].setTortuga(true);
        initMap();

        map[davyPosition.getX()][davyPosition.getY()].setDavy(true);
        map[chestPosition.getX()][chestPosition.getY()].setChest(true);
        map[tortugaPosition.getX()][tortugaPosition.getY()].setTortuga(true);
        jackPos = new Position(jackPosition);
        kraken = new Position(krakenPosition);
    }

    /**
     * Tortuga's positin getter
     * @return tortuga's position
     */
    public Position getTortugaPosition (){
        for (int i=0;i<9 ;i++){
            for (int j=0;j<9;j++)
            {
                if (fullMap[i][j].isTortuga())
                    return new Position(i,j);
            }
        }
        return new Position();
    }

    /**
     * chest's position getter
     * @return chest's position
     */
    public Position getChestPosition (){
        for (int i=0;i<9 ;i++){
            for (int j=0;j<9;j++)
            {
                if (fullMap[i][j].isChest())
                    return new Position(i,j);
            }
        }
        return new Position();
    }

    /**
     * A method that moves Jack to a neigboring cell, and updates the map discovered by Jack
     * @param newPosition the position jack wants to move to
     */
    public void moveJack(Position newPosition){
        jackPos.setPos(newPosition.getX(), newPosition.getY());
        preceive();
    }

    /**
     * Kraken's position getter
     * @return kraken's position
     */
    public Position getKrakenPosition (){
        return new Position(kraken);
    }

    /**
     * A method that checks the surroundings of jack according to the variant, and updates the map discovered by Jack
     */
    public void preceive (){
        int a = jackPos.getX();
        int b = jackPos.getY();
        Integer dx[] = {1, -1, 0, 0, 1, 1, -1, -1, 0, -2, 2, 0, 0};
        Integer dy[] = {0, 0, 1, -1, 1, -1, 1, -1, 0, 0, 0, -2, 2};
        if (variant == 1)
        {
            for (int q=0; q<9; q++)
            {
                if (a+dx[q] >=0 && a+dx[q] <9 && b+dy[q]>=0 && b+ dy[q]<9)
                {
                    map[a+dx[q]][b+dy[q]] = new Cell(fullMap[a+dx[q]][b+dy[q]].getStats());
                    if (map[a+dx[q]][b+dy[q]].isKraken())
                        kraken = new Position(a+dx[q],b+dy[q]);
                }
            }
        }
        else
        {
            for (int q=0; q<13; q++)
            {
                if (a+dx[q] >=0 && a+dx[q] <9 && b+dy[q]>=0 && b+ dy[q]<9)
                {
                    map[a+dx[q]][b+dy[q]] = new Cell(fullMap[a+dx[q]][b+dy[q]].getStats());
                    if (map[a+dx[q]][b+dy[q]].isKraken())
                        kraken = new Position(a+dx[q],b+dy[q]);
                }
            }
        }
    }

    /**
     * a method that checks a cell at a certain position (in the map discovered by jack)
     * @param position the position to check
     * @return the Cell at the specified position
     */
    public Cell lookup (Position position){
        return map[position.getX()][position.getY()];
    }

    /**
     * A method that removes the kraken (called after killing the kraken)
     */
    public void killKraken (){
        fullMap[kraken.getX()][kraken.getY()].setKraken(false);
        initMap();
    }

    /**
     * A method that revives the kraken (called when we wanna trace back in time to check other solutinos in the algorithms)
     */
    public void reviveKraken(){
        fullMap[kraken.getX()][kraken.getY()].setKraken(true);
        initMap();
    }

    /**
     * A method that updated the dangerous areas in the fullMap
     */
    public void initMap(){
        for (int i=0;i<9;i++)
        {
            for (int j=0;j<9;j++)
            {
                if (fullMap[i][j].isDangerous())
                {
                    fullMap[i][j].setDangerous(false);
                }
            }
        }
        Integer dx[] = {0, 1, -1, 0, 0, 1, 1, -1, -1};
        Integer dy[] = {0, 0, 0, 1, -1, 1, -1, 1, -1};
        for (int i=0;i<9;i++)
        {
            for (int j=0;j<9;j++)
            {
                if (fullMap[i][j].isDavy())
                {
                    for (int q=0; q<9; q++)
                    {
                        if (i+dx[q] < 9 && i +dx[q] >=0 && j +dy[q]< 9 && j + dy[q]>=0)
                        {
                            fullMap[i+dx[q]][j+dy[q]].setDangerous(true);
                        }
                    }
                }
                if (fullMap[i][j].isKraken())
                {
                    for (int q=0; q<5; q++)
                    {
                        if (i+dx[q] < 9 && i +dx[q] >=0 && j +dy[q]< 9 && j + dy[q]>=0)
                        {
                            fullMap[i+dx[q]][j+dy[q]].setDangerous(true);
                        }
                    }
                }
                if (fullMap[i][j].isRock())
                    fullMap[i][j].setDangerous(true);
            }
        }
    }

    /**
     * A method that creates a random map
     */
    public void createRandomMap(){
        Random random = new Random();
        Position jackPosition = new Position(0,0);
        Position davyPosition = new Position(random.nextInt(9), random.nextInt(9));
        Position krakenPosition = new Position(random.nextInt(9), random.nextInt(9));
        Position rockPosition = new Position(random.nextInt(9), random.nextInt(9));
        Position chestPosition = new Position(random.nextInt(9), random.nextInt(9));
        Position tortugaPosition = new Position(random.nextInt(9), random.nextInt(9));
        fullMap[jackPosition.getX()][jackPosition.getY()].setJack(true);
        fullMap[davyPosition.getX()][davyPosition.getY()].setDavy(true);
        fullMap[krakenPosition.getX()][krakenPosition.getY()].setKraken(true);
        fullMap[rockPosition.getX()][rockPosition.getY()].setRock(true);
        fullMap[chestPosition.getX()][chestPosition.getY()].setChest(true);
        fullMap[tortugaPosition.getX()][tortugaPosition.getY()].setTortuga(true);
        if (!validateMap()){
            for (int i=0; i<9; i++)
            {
                for (int j=0;j<9;j++)
                {
                    fullMap[i][j] = new Cell();
                }
            }
            createRandomMap();
        }
        else
        {
            initMap();
            map[davyPosition.getX()][davyPosition.getY()].setDavy(true);
            map[chestPosition.getX()][chestPosition.getY()].setChest(true);
            map[tortugaPosition.getX()][tortugaPosition.getY()].setTortuga(true);
            jackPos = new Position(jackPosition);
            kraken = new Position(krakenPosition);
        }
    }

    /**
     * A method to check whether the fullMap we have follows the rules of the assignment
     * @return
     */
    public Boolean validateMap(){
        for (int i=0;i<9;i++)
        {
            for (int j=0;j<9;j++)
            {
                if(fullMap[i][j].isJack() && fullMap[i][j].isDangerous())
                    return false;

                if(fullMap[i][j].isChest() && fullMap[i][j].isDangerous())
                    return false;
                if(fullMap[i][j].isChest() && fullMap[i][j].isJack())
                    return false;

                if(fullMap[i][j].isTortuga() && fullMap[i][j].isDangerous())
                    return false;
                if(fullMap[i][j].isTortuga() && fullMap[i][j].isChest())
                    return false;

                if(fullMap[i][j].isDavy() && fullMap[i][j].isRock())
                    return false;
                if(fullMap[i][j].isDavy() && fullMap[i][j].isKraken())
                    return false;

                if (fullMap[i][j].isRock() && fullMap[i][j].isTortuga())
                    return false;

            }
        }
        return true;
    }
}

/**
 * A class that represents the actor (Jack), has the implementation of the algorithms, and also has a Sea which he will get his necessary data from (without the ability to
 * get data that he can't get according to the rules of the assignment)
 */
class Jack{
    private Sea sea;
    Position jackPos, tortugaPos, chestPos, krakenPos;

    /**
     * Default constructor
     */
    public Jack (){}

    /**
     * A constructor that takes in multiple parameters and sets them to the attributes
     * @param sea the sea which Jack has to discover
     * @param jackPos the position of Jack
     * @param tortugaPos the position of Tortuga
     * @param chestPos the position of the Dead Man's Chest
     */
    public Jack (Sea sea, Position jackPos, Position tortugaPos, Position chestPos){
        this.sea = sea;
        this.jackPos = jackPos;
        this.tortugaPos = tortugaPos;
        this.chestPos = chestPos;
        krakenPos = new Position();
    }

    /**
     * A method that calculates the length of a path
     * @param path the path to calculate the length of
     * @return the length of the path
     */
    public int pathLength (Position path[]){
        int ret=0;
        for (int i=0;;i++){
            if (path[i] == null || path[i].getX() == -1)
                break;
            ret++;
        }
        return ret;
    }

    /**
     * A method that checks whether the path is valid or not (the last position in the path is the desired position, which is dest)
     * @param path the path
     * @param dest the destinatino
     * @return true if it's invalid, false otherwise
     */
    public Boolean invalidPath (Position path[], Position dest){
        if (pathLength(path) == 0)
            return true;
        if (!path[pathLength(path)-1].equals(dest))
            return true;
        return false;
    }

    /**
     * A method that handles the A* algorithm, where it tries to go from Jack to the Chest right away, or from jack to tortuga to one of the krakens killing sides
     * and finally to the chest. Evaluates which path is best and returns it.
     * @return best path, if no such path is found returns an empty path
     */
    public Position[] aStar(){
        Position finalPath[] = aStar(jackPos, chestPos);
        Position currentPath[] = new Position[81];
        if (sea.getKrakenPosition().getX() == -1)
        {
            if (invalidPath(finalPath, chestPos))
                return new Position[81];
            return finalPath;
        }
        krakenPos = new Position(sea.getKrakenPosition());
        int dx[] = {-1, -1, 1, 1};
        int dy[] = {1, -1, 1, -1};
        for (int i=pathLength(finalPath)-2;i>=0;i--){
            moveTo(finalPath[i]);
        }

        currentPath = aStar(jackPos, tortugaPos);

        for (int i=0; i<4;i++)
        {
            int x = dx[i]+krakenPos.getX();
            int y = dy[i]+krakenPos.getY();
            if (x >= 0 && x < 9 && y >=0 && y < 9)
            {

                Position path1[] = aStar(jackPos, new Position(x,y));

                if (pathLength(path1) > 0 && path1[pathLength(path1)-1].equals(new Position(x,y)))
                {
                    sea.killKraken();
                    sea.preceive();
                    Position path2[] = aStar(jackPos, chestPos);
                    sea.reviveKraken();
                    if (pathLength(path2) > 0 && (pathLength(finalPath) ==0 || pathLength(currentPath) + pathLength(path1) + pathLength(path2) -2 < pathLength(finalPath)))
                    {
                        finalPath = new Position[81];
                        int cnt = 0;
                        for (int j=0;j<pathLength(currentPath);j++)
                        {
                            finalPath[cnt] = currentPath[j];
                            cnt++;
                        }

                        for (int j=1;j<pathLength(path1);j++)
                        {
                            finalPath[cnt] = path1[j];
                            cnt++;
                        }

                        for (int j=1;j<pathLength(path2);j++)
                        {
                            finalPath[cnt] = path2[j];
                            cnt++;
                        }
                    }
                    for (int j=pathLength(path2)-2;j>=0;j--){
                        moveTo(path2[j]);
                    }
                }
                for (int j=pathLength(path1)-2;j>=0;j--){
                    moveTo(path1[j]);
                }
            }
        }
        for (int j=pathLength(currentPath)-2;j>=0;j--){
            moveTo(currentPath[j]);
        }
        if (invalidPath(finalPath, chestPos))
            return new Position[81];
        return finalPath;
    }

    /**
     * A method where the essence of the A* algorithm lay, it checks for the best path from the start position to the dest position and returns it.
     * The heuristics are the Mannhaten distance from the dest.
     * @param start start position
     * @param dest destanation position
     * @return best path, if no such path is found returns an empty path
     */
    public Position[] aStar (Position start, Position dest){
        Position finalPath[] = new Position[81];
        sea.preceive();
        if (sea.lookup(start).isDangerous())
            return finalPath;
        TreeSet<Node> queue = new TreeSet<Node>();
        int h[][] = new int[9][9];
        for (int i=0;i<9;i++)
        {
            for (int j=0; j<9; j++)
            {
                h[i][j] = (Math.abs(i-dest.getX())+ Math.abs(j-dest.getY()));
            }
        }
        Node jackNode = new Node(jackPos);
        jackNode.addPosition(start);
        queue.add(jackNode);
        int dx[] = {1, -1, 0, 1, -1, 0, 1, -1};
        int dy[] = {1, 1, 1, -1 ,-1 ,-1, 0, 0};
        while (!queue.isEmpty())
        {
            Node currentNode = queue.pollFirst();
            preprocessJackPos(jackNode, currentNode);
            jackNode.setCell(currentNode.getCell());
            if (currentNode.getPathLength() > 25 || currentNode.getCell().equals(dest))
            {
                for (int i=0;i < currentNode.getPathLength();i++){
                    finalPath[i] = new Position(currentNode.getPath()[i]);
                }
                break;
            }
            for (int i=0; i<8 ;i++)
            {
                int x = jackPos.getX()+dx[i];
                int y = jackPos.getY()+dy[i];
                if (x >= 0 && x < 9 && y >= 0 && y < 9)
                {
                    Node newNode = new Node(new Position(x,y));
                    for (int j=0; j<currentNode.getPathLength(); j++)
                    {
                        newNode.addPosition(currentNode.getPath()[j]);
                    }
                    newNode.addPosition(new Position(x,y));
                    newNode.setVal(currentNode.getPathLength() + h[x][y] + 1 + ((sea.lookup(new Position(x,y)).isDangerous()) ? 1000: 0));
                    queue.add(newNode);
                }
            }
        }
        return finalPath;
    }

    /**
     * A method that handles the Backtrack algorithm, where it tries to go from Jack to the Chest right away, or from jack to tortuga to one of the krakens killing sides
     * and finally to the chest. Evaluates which path is best and returns it.
     * @return best path, if no such path is found returns an empty path
     */
    public Position[] backTrack (){
        Position finalPath[] = backTrack(jackPos, chestPos);
        Position currentPath[] = new Position[81];
        if (sea.getKrakenPosition().getX() == -1)
        {
            if (invalidPath(finalPath, chestPos))
                return new Position[81];
            return finalPath;
        }
        krakenPos = new Position(sea.getKrakenPosition());
        int dx[] = {-1, -1, 1, 1};
        int dy[] = {1, -1, 1, -1};
        currentPath = backTrack(jackPos, tortugaPos);
        moveTo(currentPath);
        for (int i=0; i<4;i++)
        {
            int x = dx[i]+krakenPos.getX();
            int y = dy[i]+krakenPos.getY();
            if (x >= 0 && x < 9 && y >=0 && y < 9)
            {
                Position path1[] = backTrack(jackPos, new Position(x,y));
                moveTo(path1);
                if (pathLength(path1) > 0 && path1[pathLength(path1)-1].equals(new Position(x,y)))
                {
                    sea.killKraken();
                    sea.preceive();
                    Position path2[] = backTrack(jackPos, chestPos);
                    moveTo(path2);
                    sea.reviveKraken();
                    if (pathLength(path2) > 0 && (pathLength(finalPath) ==0 || pathLength(currentPath) + pathLength(path1) + pathLength(path2) -2 < pathLength(finalPath)))
                    {
                        finalPath = new Position[81];
                        int cnt = 0;
                        for (int j=0;j<pathLength(currentPath);j++)
                        {
                            finalPath[cnt] = currentPath[j];
                            cnt++;
                        }

                        for (int j=1;j<pathLength(path1);j++)
                        {
                            finalPath[cnt] = path1[j];
                            cnt++;
                        }

                        for (int j=1;j<pathLength(path2);j++)
                        {
                            finalPath[cnt] = path2[j];
                            cnt++;
                        }
                    }
                    for (int j=pathLength(path2)-2;j>=0;j--){
                        moveTo(path2[j]);
                    }
                }
                for (int j=pathLength(path1)-2;j>=0;j--){
                    moveTo(path1[j]);
                }
            }
        }
        for (int j=pathLength(currentPath)-2;j>=0;j--){
            moveTo(currentPath[j]);
        }
        if (invalidPath(finalPath, chestPos))
            return new Position[81];
        return finalPath;
    }

    /**
     * A method where the essence of the backtrack algorithm lay. DFS with hueristics was implemented, since DFS alone would take forever to finish evaluating.
     * The number of times the DFS will be called is defined by maxIterationCounter = 10000;
     * @param start the start position
     * @param dest the destanation position
     * @return best path found, if no such path is found returns an empty path
     */
    public Position[] backTrack (Position start, Position dest){
        Position finalPath[] = new Position[81];
        sea.preceive();
        if (sea.lookup(start).isDangerous())
            return finalPath;
        int h[][] = new int[9][9];
        Boolean visited[][] = new Boolean[9][9];
        for (int i=0;i<9;i++)
        {
            for (int j=0; j<9; j++)
            {
                h[i][j] = (Math.abs(i-dest.getX())+ Math.abs(j-dest.getY()));
                visited[i][j] = false;
            }
        }
        Node jackNode = new Node(jackPos);
        jackNode.addPosition(start);
        visited[start.getX()][start.getY()] = true;
        int dx[] = {1, 1, 1, -1, -1, -1, 0, 0};
        int dy[] = {1, -1, 0, 1 , -1, 0, 1, -1};
        class Local{
            private int iterationCounter = 0;
            private int maxIterationCounter = 10000;
            void dfs(Position currentPos){
                iterationCounter ++;
                if (currentPos.equals(dest)){
                    if (pathLength(finalPath) == 0 || pathLength(finalPath) > pathLength(jackNode.getPath()))
                    {
                        for (int i=0;i<pathLength(finalPath) ;i++)
                            finalPath[i] = null;
                        for (int i=0;i<pathLength(jackNode.getPath()) ;i++)
                            finalPath[i] = new Position(jackNode.getPath()[i]);
                    }
                    return;
                }
                Boolean done[] = new Boolean[8];
                for (int i=0;i<8;i++){
                    done[i] = false;
                }
                for (int q=0 ; q<8; q++)
                {
                    int nextIndex = -1;
                    int currentMin = 100;
                    for (int i=0;i<8;i++){
                        int x = currentPos.getX() + dx[i];
                        int y = currentPos.getY() + dy[i];
                        if (!done[i])
                        {
                            if (x >=0 && x<9 && y>=0 && y<9 && h[x][y]<currentMin)
                            {
                                nextIndex = i;
                                currentMin = h[x][y];
                            }
                        }
                    }
                    if (nextIndex == -1)
                        continue;
                    int x = currentPos.getX() + dx[nextIndex];
                    int y = currentPos.getY() + dy[nextIndex];
                    if (!visited[x][y] && !(sea.lookup(new Position(x,y)).isDangerous())){
                        if (iterationCounter < maxIterationCounter)
                        {
                            visited[x][y] = true;
                            jackNode.addPosition(new Position(x,y));
                            moveTo(new Position(x,y));
                            dfs(new Position(x,y));
                            moveTo(currentPos);
                            jackNode.popPath();
                            visited[x][y]=false;
                        }
                    }
                    done[nextIndex] = true;
                }
            }
        }
        new Local().dfs(start);
        return finalPath;
    }

    /**
     * A method that moves jack to a neighbooring position, updates in the sea object as well
     * @param newPosition the position we want jack to move to
     * @return true if jack can move there, false otherwise
     */
    public Boolean moveTo(Position newPosition){
        if (jackPos.equals(newPosition))
            return false;
        jackPos = new Position(newPosition);
        sea.moveJack(newPosition);
        return true;
    }

    /**
     * A method that moves jacks multiple positions.
     * @param path the path we want jack to traverse
     * @return true if jack can move there, false otherwise
     */
    public Boolean moveTo(Position path[]){
        for (int i=0;i<pathLength(path);i++)
        {
            moveTo(path[i]);
        }
        return true;
    }

    /**
     * A method that helps in the implementaions of the algorithms, it takes in two nodes and find the longest common prefix of their paths.
     * @param node1 the first node
     * @param node2 the second node
     * @return the index of the last common prefix
     */
    public int findCommonNode(Node node1, Node node2){
        int ret = 0;
        Position path1[] = node1.getPath();
        Position path2[] = node2.getPath();
        for (int i=0;i<node1.getPathLength() && i<node2.getPathLength();i ++)
        {
            if (!path1[i].equals(path2[i]))
                return ret;
            ret++;
        }
        return ret;
    }

    /**
     * A method that helps in the implementaions of the algorithms, it preprocesses jack's position so the algorithm flow is stable with the movement of Jack
     * @param node1 the first node
     * @param node2 the second node
     */
    public void preprocessJackPos(Node node1, Node node2){
        int newLength = findCommonNode(node1, node2);
        while (node1.getPathLength()!= newLength){
            moveTo(node1.popPath());
        }
        for (int i=newLength; i<node2.getPathLength() ;i++)
        {
            moveTo(node2.getPath()[i]);
        }
    }
}

/*
 * The main class where input, output, and evaluation are handled. (To test 1000 random maps, you can call evaluateAlgorithms in the main method)
 */
public class Main {

    /**
     * A method to calculate the mean of an array of length size
     * @param time the array
     * @param size the size of the array
     * @return the mean
     */
    public static double mean(double time[], double size){
        double mean = 0;
        for (int i=0;i<size;i++)
        {
            mean += time[i]/size;
        }
        return mean;
    }

    /**
     * A method to calculate the standard deviation of an array of length size
     * @param time the array
     * @param size the size of the array
     * @return the standard deviation
     */
    public static double standardDeviation (double time[], double size){
        double mean = mean(time, size);
        double nom = 0;
        for (int i=0;i<size ;i++){
            nom += (time[i]-mean) * (time[i]-mean);
        }
        return Math.sqrt(nom/size);
    }

    /**
     * A method to calculate the median of an array of length size
     * @param time the array
     * @param size the size of the array
     * @return the median
     */
    public static double median (double time[], double size){
        Arrays.sort(time);
        return (time[499] + time[500])/2.0;
    }

    /**
     * A method to evaluate the algorithms and print the data required for the statistical analysis
     */
    public static void evaluateAlgorithms(){
        double time[]= new double[1000];
        int wins = 0;
        for (int i=0;i<1000;i++)
        {
            Sea sea = new Sea(1);
            Jack jack = new Jack(sea, new Position(0,0), sea.getTortugaPosition(), sea.getChestPosition());
            long startTimeBacktrack= System.nanoTime();
            Position pp[] =jack.backTrack();
            if (jack.pathLength(pp) != 0)
                wins++;
            long endTimeBacktrack= System.nanoTime();
            time[i] = (endTimeBacktrack-startTimeBacktrack)/1e6;
        }
        System.out.println("Backtrack (Variant 1):");
        System.out.println("Precentage of wins: "+ (wins/1000.0) *100.0 + "%");
        System.out.println("The mean: "+ mean(time, 1000));
        System.out.println("The median: "+ median(time, 1000));
        System.out.println("The standard deviation: " + standardDeviation(time, 1000));
        System.out.println("------------------------");
        wins=0;
        for (int i=0;i<1000;i++)
        {
            Sea sea = new Sea(1);
            Jack jack = new Jack(sea, new Position(0,0), sea.getTortugaPosition(), sea.getChestPosition());
            long startTimeBacktrack= System.nanoTime();
            Position pp[] =jack.aStar();
            if (jack.pathLength(pp) != 0)
                wins++;
            long endTimeBacktrack= System.nanoTime();
            time[i] = (endTimeBacktrack-startTimeBacktrack)/1e6;
        }
        System.out.println("A Star (Variant 1):");
        System.out.println("Precentage of wins: "+ (wins/1000.0) *100.0 + "%");
        System.out.println("The mean: "+ mean(time, 1000));
        System.out.println("The median: "+ median(time, 1000));
        System.out.println("The standard deviation: " + standardDeviation(time, 1000));
        System.out.println("------------------------");
        wins=0;
        for (int i=0;i<1000;i++)
        {
            Sea sea = new Sea(2);
            Jack jack = new Jack(sea, new Position(0,0), sea.getTortugaPosition(), sea.getChestPosition());
            long startTimeBacktrack= System.nanoTime();
            Position pp[] =jack.backTrack();
            if (jack.pathLength(pp) != 0)
                wins++;
            long endTimeBacktrack= System.nanoTime();
            time[i] = (endTimeBacktrack-startTimeBacktrack)/1e6;
        }
        System.out.println("Backtrack (Variant 2):");
        System.out.println("Precentage of wins: "+ (wins/1000.0) *100.0 + "%");
        System.out.println("The mean: "+ mean(time, 1000));
        System.out.println("The median: "+ median(time, 1000));
        System.out.println("The standard deviation: " + standardDeviation(time, 1000));
        System.out.println("------------------------");
        wins=0;
        for (int i=0;i<1000;i++)
        {
            Sea sea = new Sea(2);
            Jack jack = new Jack(sea, new Position(0,0), sea.getTortugaPosition(), sea.getChestPosition());
            long startTimeBacktrack= System.nanoTime();
            Position pp[] =jack.aStar();
            if (jack.pathLength(pp) != 0)
                wins++;
            long endTimeBacktrack= System.nanoTime();
            time[i] = (endTimeBacktrack-startTimeBacktrack)/1e6;
        }
        System.out.println("A star (Variant 2):");
        System.out.println("Precentage of wins: "+ (wins/1000.0) *100.0 + "%");
        System.out.println("The mean: "+ mean(time, 1000));
        System.out.println("The median: "+ median(time, 1000));
        System.out.println("The standard deviation: " + standardDeviation(time, 1000));
        System.out.println("------------------------");


    }

    /**
     * A method to handle input and output
     * @throws IOException
     */
    public static void init () throws IOException {
        System.out.println("Do you want to enter the positions using an input file? [Y/N]");
        Scanner sscanner = new Scanner(System.in);
        String q1 = sscanner.nextLine();
        if (new String(q1).equals("Y") || new String(q1).equals("y")) {
            File input = new File("input.txt");
            Scanner scanner = new Scanner(input);
            String pos = scanner.nextLine();
            int var = scanner.nextInt();
            Position x[] = new Position[6];
            int cnt = 0;
            for (int i = 1; i < pos.length(); i += 6) {
                x[cnt] = new Position(Character.getNumericValue(pos.charAt(i)), Character.getNumericValue(pos.charAt(i + 2)));
                cnt++;
            }
            Sea sea = new Sea(var, x[0], x[1], x[2], x[3], x[4], x[5]);
            Jack jack = new Jack(sea, x[0], x[5], x[4]);
            long startTimer = System.nanoTime();
            Position pp[] = jack.aStar();
            long endtTimer = System.nanoTime();
            FileWriter outputAStar = new FileWriter("outputAStar.txt");

            if (jack.pathLength(pp) == 0) {
                outputAStar.write("Lose");
                outputAStar.close();

            } else {
                char[][] map1 = new char[9][9];
                outputAStar.write("Win \n" + (jack.pathLength(pp) - 1) + "\n");
                for (int i = 0; i < jack.pathLength(pp); i++) {
                    outputAStar.write("[" + pp[i].getX() + "," + pp[i].getY() + "] ");
                    map1[pp[i].getX()][pp[i].getY()] = '*';
                }
                outputAStar.write("\n-------------------\n ");
                outputAStar.write(" ");
                for (int i = 0; i < 9; i++)
                    outputAStar.write(i + " ");
                outputAStar.write("\n");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (map1[i][j] != '*')
                            map1[i][j] = '-';
                    }
                }
                for (int i = 0; i < 9; i++) {
                    outputAStar.write(i + " ");
                    for (int j = 0; j < 9; j++)
                        outputAStar.write(map1[i][j] + " ");
                    outputAStar.write("\n");
                }
                outputAStar.write("-------------------\n");
                outputAStar.write((endtTimer - startTimer) / 1e6 + " ms");
                outputAStar.close();
            }
            long startTimerBT = System.nanoTime();
            Position pb[] = jack.backTrack();
            long endtTimerBT = System.nanoTime();
            FileWriter outputBacktracking = new FileWriter("outputBacktracking.txt");
            if (jack.pathLength(pb) == 0) {
                outputBacktracking.write("Lose");
                outputBacktracking.close();
            } else {
                char[][] map2 = new char[9][9];
                outputBacktracking.write("Win \n" + (jack.pathLength(pb) - 1) + "\n");
                for (int i = 0; i < jack.pathLength(pb); i++) {
                    outputBacktracking.write("[" + pb[i].getX() + "," + pb[i].getY() + "] ");
                    map2[pb[i].getX()][pb[i].getY()] = '*';
                }
                outputBacktracking.write("\n-------------------\n ");
                outputBacktracking.write(" ");
                for (int i = 0; i < 9; i++)
                    outputBacktracking.write(i + " ");
                outputBacktracking.write("\n");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (map2[i][j] != '*')
                            map2[i][j] = '-';
                    }
                }
                for (int i = 0; i < 9; i++) {
                    outputBacktracking.write(i + " ");
                    for (int j = 0; j < 9; j++)
                        outputBacktracking.write(map2[i][j] + " ");
                    outputBacktracking.write("\n");
                }
                outputBacktracking.write("-------------------\n");
                outputBacktracking.write((endtTimerBT - startTimerBT) / 1e6 + " ms");
                outputBacktracking.close();
            }
        }
        else if (new String(q1).equals("N")|| new String(q1).equals("n")){
            System.out.println("A random map will be generated.\nWhat variant would you like to apply?");
            int q2 = sscanner.nextInt();
            if (q2 == 1 || q2 == 2) {
                Sea sea = new Sea(q2);
                Jack jack = new Jack(sea, new Position(0, 0), sea.getTortugaPosition(), sea.getChestPosition());

                long startTimer = System.nanoTime();
                Position pp[] = jack.aStar();
                long endTimer = System.nanoTime();

                FileWriter outputAStar = new FileWriter("outputAStar.txt");
                if (jack.pathLength(pp) == 0) {
                    outputAStar.write("Lose");
                    outputAStar.close();
                } else {
                    char[][] map1 = new char[9][9];
                    outputAStar.write("Win \n" + (jack.pathLength(pp) - 1) + "\n");
                    for (int i = 0; i < jack.pathLength(pp); i++) {
                        outputAStar.write("[" + pp[i].getX() + "," + pp[i].getY() + "] ");
                        map1[pp[i].getX()][pp[i].getY()] = '*';
                    }
                    outputAStar.write("\n-------------------\n ");
                    outputAStar.write(" ");
                    for (int i = 0; i < 9; i++)
                        outputAStar.write(i + " ");
                    outputAStar.write("\n");
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (map1[i][j] != '*')
                                map1[i][j] = '-';
                        }
                    }
                    for (int i = 0; i < 9; i++) {
                        outputAStar.write(i + " ");
                        for (int j = 0; j < 9; j++)
                            outputAStar.write(map1[i][j] + " ");
                        outputAStar.write("\n");
                    }
                    outputAStar.write("-------------------\n");
                    outputAStar.write((endTimer - startTimer) / 1e6 + " ms");
                    outputAStar.close();
                }

                long startTimerBT = System.nanoTime();
                Position pb[] = jack.backTrack();
                long endTimerBT = System.nanoTime();
                FileWriter outputBacktracking = new FileWriter("outputBacktracking.txt");
                if (jack.pathLength(pb) == 0) {
                    outputBacktracking.write("Lose");
                    outputBacktracking.close();
                } else {
                    char[][] map2 = new char[9][9];
                    outputBacktracking.write("Win \n" + (jack.pathLength(pb) - 1) + "\n");
                    for (int i = 0; i < jack.pathLength(pb); i++) {
                        outputBacktracking.write("[" + pb[i].getX() + "," + pb[i].getY() + "] ");
                        map2[pb[i].getX()][pb[i].getY()] = '*';
                    }
                    outputBacktracking.write("\n-------------------\n ");
                    outputBacktracking.write(" ");
                    for (int i = 0; i < 9; i++)
                        outputBacktracking.write(i + " ");
                    outputBacktracking.write("\n");
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (map2[i][j] != '*')
                                map2[i][j] = '-';
                        }
                    }
                    for (int i = 0; i < 9; i++) {
                        outputBacktracking.write(i + " ");
                        for (int j = 0; j < 9; j++)
                            outputBacktracking.write(map2[i][j] + " ");
                        outputBacktracking.write("\n");
                    }
                    outputBacktracking.write("-------------------\n");
                    outputBacktracking.write((endTimerBT - startTimerBT) / 1e6 + " ms");
                    outputBacktracking.close();
                }
            }
            else{
                System.out.println("Wrong variant number.");
            }
        }
        else
            System.out.println("Wrong input.");
    }
    public static void main(String[] args) throws IOException{
        init();
        //remove the comment from the following line to evaluate 1000 random maps.
        //evaluateAlgorithms();
    }
}