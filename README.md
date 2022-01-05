# Ball-Droplet-Game

>>---------------------------------------------------------------------------------<<
                                 Game of Ball/Droplets
Ball/ Droplets is a game that players are challenged to stay alive while
struggling with the moving objects causing their lives. The game ends when
the player does not have any health remaining. The game contains two main
objects, i.e. Ball and Droplets.
• Ball moves freely in the frame and allows the player to gain life in any
time. However, the collision between Ball and Droplets causes the lives
of the player.
• Droplets move only in vertical axis. The movement of Droplet starts from
top of the frame; once Droplet reaches the floor, the player losses some of
their lives.

The life gain/loss is summarized as the following formula:


         | +1, if player clicks Ball
life =   | +3, if player clicks Droplet
         | −3, if Droplet hits floor
