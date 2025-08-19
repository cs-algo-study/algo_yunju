import java.util.*;
 
public class SWEA_1873 {
 
    static char[][] map;
    static int[] dirX = {-1, 1, 0, 0}; 
    static int[] dirY = {0, 0, -1, 1}; 
    static int tankX, tankY, tankD;
     
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for(int k=1; k<=T; k++) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            map = new char[H][W];
            tankD = 0; tankX = 0; tankY = 0;
            for(int i=0; i<H; i++) {
                String line = sc.next();
                for(int j=0; j<W; j++) {
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == '^') {
                        tankX = i;
                        tankY = j;
                        tankD = 0;
                    } else if(map[i][j] == 'v') {
                        tankX = i;
                        tankY = j;
                        tankD = 1;
                    } else if(map[i][j] == '<') {
                        tankX = i;
                        tankY = j;
                        tankD = 2;
                    } else if(map[i][j] == '>') {
                        tankX = i;
                        tankY = j;
                        tankD = 3;
                    }
                }
            }   
                     
            int N = sc.nextInt();
            String line = sc.next();
                 
            for(int i=0; i<N; i++) {
                if(line.charAt(i) == 'S') {
                    shoot();
                } else {
                    move(line.charAt(i));
                }
            }
            System.out.print("#" + k + " ");
             
            for(char[] str : map) {
                System.out.println(new String(str));
            }
        }           
    }
     
    public static boolean isMap(int dx, int dy) {
        if(dx>=0 && dx<map.length && dy>=0 && dy<map[dx].length) {
            return true;
        } 
        return false;
    }
     
    public static void shoot() {
        int dx = tankX + dirX[tankD];
        int dy = tankY + dirY[tankD];
         
        while(isMap(dx, dy)) {
            if(map[dx][dy] == '#') {
                return;
            } else if(map[dx][dy] == '*') {
                map[dx][dy] = '.';
                return;
            }
            dx += dirX[tankD];
            dy += dirY[tankD];
        }
    }
     
    public static void move(char d) {
        switch(d){
        case 'U': tankD = 0; map[tankX][tankY] = '^'; break;
        case 'D': tankD = 1; map[tankX][tankY] = 'v'; break;
        case 'L': tankD = 2; map[tankX][tankY] = '<'; break;
        case 'R': tankD = 3; map[tankX][tankY] = '>'; break;
        }
        int dx = tankX + dirX[tankD];
        int dy = tankY + dirY[tankD];
        if(!isMap(dx, dy)) {
            return;
        }
        if(map[dx][dy] == '.') {
            map[dx][dy] = map[tankX][tankY];
            map[tankX][tankY] = '.';
            tankX = dx;
            tankY = dy;
        }
    }
}