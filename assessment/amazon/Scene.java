package com.amazon;

import java.util.*;
import java.util.stream.Collectors;
/*
You are working on a project for Amazon Video and need to cut films into
scenes. To help streamline the creation of the final films, the team needs to develop
an automated way of breaking up individual shots (short sequence from a particular camera angle)
in a film into scenes (a sequence of shots).
There is already an algorithm that breaks the film up into shots and labels them with a letter.
Identical shots are labelled with the same letter.

Write a function which will partition a sequence of shot labels into scenes
so that no shot labels appear in more then one scene.
The output should be the length of each scene.

Input
The input to the function/method consists of an argument - inputList,
a list of character representing the sequence of shots.

Output
Return a list of integers representing the length of each scene, in the order in which
it appears in the given  sequence of shots.

Examples

Example 1:
Input:
Character[] array = {'a', 'b', 'a', 'b', 'c', 'b', 'a', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h','e','g','h','k', 'l','i','j'};

Output:
[9,7,8]

Explanation:
The first scene consist of the shots a,b,c.
The second scene consists of d,e,f,g.
Finally, the last scene consists of h,i,j,k.
The answer is 9,7,8 because a,b,c only appear in the forst 9 characters, then d,e,f,g appear in the next 7.
The final 8 characters consist entirely of h,i,j,k.

Example 2:

Input:
inputList = [a,b,c]

Output:
[1,1,1]

Because there are no recurring shots, all shots can be in the minimal
length 1 subsequence.

Example 3
Input:
inputList = [a,b,c,a]

Output:
[4]
Explanation:
Because 'a' appears more than once, everything between the first and last
appearance of 'a' must be in the same list.

 */
public class Scene {

    public static void main(String[] args) {

        //input
        Character[] array = {'a', 'b', 'a', 'b', 'c', 'b', 'a', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h','i','j','h','k','l','i','j'};
        //Character[] array = {'a', 'b', 'c'};
        //Character[] array = {'a', 'b', 'c', 'a'};
        List<Character> inputList = Arrays.stream(array).collect(Collectors.toList());
        //out [9,7,8]

        SceneSolution sl = new SceneSolution();
        List<Integer> out = sl.lengthEachScene(inputList);

        System.out.println("output: ");
        for (Integer integer : out) {
            System.out.print(integer + " ");
        }
    }

}

class SceneSolution {

    public List<Integer> lengthEachScene(List<Character> inputList) {

        List<Integer> result = new ArrayList<>();

        //initialize dictionary a  0 8; b 1 5; ...;
        List<ScenePoint> points = new ArrayList<>();
        for (int i = 0; i < inputList.size(); ++i) {
            Character character = inputList.get(i);

            //do not process one point twice or more
            if (listContainsThisChar(character, points)) {
                continue;
            }

            int start = i;
            int end = i;

            //calculate end
            for (int j=i+1; j < inputList.size(); ++j) {
                if (inputList.get(j).equals(character)) {
                    end = j;
                }
            }

            //do not add one point twice or more
            ScenePoint p = new ScenePoint(character, start, end);
            if (!listContainsThisChar(character, points)) {
                points.add(p);
            }
        }

        //how many elements stop here at inputList[i]?
        int startScene = 0;
        int endScene = 0;

        List<Character> uniqueChars = new ArrayList<>();

        for (int i=0; i < inputList.size(); ++i) {
            Character c = inputList.get(i);

            if(!uniqueChars.contains(c)) {
                uniqueChars.add(c);
            }

            int count = 0;
            for (ScenePoint p : points) {

                if (p.end <= i) {
                    count++;
                }

            }

            //if number of unique elements == number of elements that stops here
            //add this scene to result
            endScene = i;
            if(uniqueChars.size() == count) {
                int interval = Math.abs(endScene - startScene) + 1;

                startScene = i+1;
                endScene = i+1;
                result.add(interval);
            }
        }

        return result;
    }

    public boolean listContainsThisChar(Character c, List<ScenePoint> ps) {
        for (ScenePoint p : ps) {
            if (p.c.equals(c)) return true;
        }
        return false;
    }

}

class ScenePoint {
    Character c;
    int start;
    int end;

    public ScenePoint(Character c, int start, int end) {
        this.c = c;
        this.start = start;
        this.end = end;
    }
}

/*
input as a List
List<Character> list =  new ArrayList<>();
        list.add('a');
        list.add('b');
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('b');
        list.add('a');
        list.add('c');
        list.add('a');
        list.add('d');
        list.add('e');
        list.add('f');
        list.add('e');
        list.add('g');
        list.add('d');
        list.add('e');
        list.add('h');
        list.add('i');
        list.add('j');
        list.add('h');
        list.add('k');
        list.add('l');
        list.add('i');
        list.add('j');
 */