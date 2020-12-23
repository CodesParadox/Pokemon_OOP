# OOP_Ex2_PokemonGame
Third assignment in OOP course. 

![image](https://user-images.githubusercontent.com/69432977/102926451-aee92000-449d-11eb-9ad8-a9c82c5a78f2.png)


# Pokemon Game

**Author:** Gal Braymok.



## Description
This game takes place on a graph, containing nodes and edges, and each edge has some edges takes more time to cross then others.

You can choose from level 1 to 24 [0,23] of play, each has a different amount of pokemons and agants.

The main goal is to catch pokemons most as you can.

This game operate itself, AKA auto mode, and you will watch the game plays automatically, moving the pokemons and the agants within the graph algorithms functions.


**Structure**:
>
> - `Node` - represents the set of operations applicable on a node (vertex) in a (directional) weighted graph. 
>
> - `Edge` - represents the set of operations applicable on a directional edge(src,dest) in a (directional) weighted graph.
>
> - `DWGraph_DS` -  represents a directional weighted graph
>
> - `DWGraph_Algo` - represents a Directed (positive) Weighted Graph Theory Algorithms
>
> - `geolocation1` - represents a geo location <x,y,z>, aka Point3D
>
> - `JsonDeserial` - exibit directed weighted graph from the Json String.



**Game Client**:
>
> - `Arena`- This class represents a multi Agents Arena which move on a graph - grabs Pokemons and avoid the Zombies
>
> - `CL_Agent` - represents the set of catched pokemons that operations fit on an agent catch them.
>
> - `CL_Pokemon`- set of pokemons in the game
>
> - `FormatWindow`- panel of the GUI using JFrame method 
> 
> - `Panelim`- frame for the GUI using JPanel method
>
> - `Ex2`- the main class game



<img width="736" alt="ללא שם" src="https://user-images.githubusercontent.com/69432977/103016871-7273fd80-454b-11eb-919f-8a6182f92d7a.png">





