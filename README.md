# Project 5 - Graphical Adventure Game
## _Zhengtian Zhang_

## About

In this version of our adventure game, I added an option to play the game using an interactive graphical user interface (GUI).

## Features

The GUI is able to:
- expose all game settings including the size of the dungeon, the interconnectivity, whether it is wrapping or not, the percentage of caves that have treasure, and the number of Otyughs through one or more items on a JMenu (Links to an external site.) 
- provide an option for quitting the game, restarting the game as a new game with a new dungeon or reusing the same dungeon through one or more items on a JMenu (Links to an external site.)
- display the dungeon to the screen using graphical representation. The view should begin with a mostly blank screen and display only the pieces of the maze that have been revealed by the user's exploration of the caves and tunnels. Dungeons that are bigger than the area allocated it to the screen should provide the ability to scroll the view.
- allow the player to move through the dungeon using a mouse click on the screen in addition to the keyboard arrow keys. A click on an invalid space in the game would not advance the player.
- display the details of a dungeon location to the screen. For instance, does it have treasure, does it have an arrow, does it smell.
- provide an option to get the player's description
- provide an option for the player to pick up a treasure or an arrow through pressing a key on the keyboard.
- an option for the player to shoot an arrow by pressing a key on the keyboard followed by an arrow key to indicate the direction.
- provide a clear indication of the results of each action a player takes.
- play sound effects accordingly.



## How To Run

```sh
java -jar path/GraphicalAdvantureGame.jar
```
## How To Use the program

- Run the program using the command above. 
- Press the arrow keys, click the buttons on the screen or click on the map to move the player.
- Press 's' followed by an arrow key and a number between 1 to 5 to shoot an arrow.
- Press 'p' to pick up treasure/arrow in current cave/tunnel.
- Use the menu to view the settings, restart the game or quit the game.


## Description of Example

Please refer to the screen shot.

## Design/Model Changes

- Add a new View interface and implement it in DungeonView.
- Add several class as part of the GUI.
- Add new methods in the model.
- Rewrite the controller.

## Assumptions

- Treasure and arrow can be found in both tunnels and caves.
- Player can stay in tunnels.

## Limitations

- The GUI is not very pretty.

## Citations
Wikipedia
Kruskal's algorithm
https://en.wikipedia.org/wiki/Kruskal's_algorithm
2021.Apr.25