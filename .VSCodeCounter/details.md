# Details

Date : 2021-11-11 09:29:34

Directory c:\Users\carlc\Desktop\WSS\WSS code\OmniDrive-Carl

Total : 85 files,  4806 codes, 1099 comments, 1526 blanks, all 7431 lines

[summary](results.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [.gitattributes](/.gitattributes) | Properties | 1 | 1 | 1 | 3 |
| [.wpilib/wpilib_preferences.json](/.wpilib/wpilib_preferences.json) | JSON | 6 | 0 | 4 | 10 |
| [build.gradle](/build.gradle) | Groovy | 47 | 27 | 10 | 84 |
| [gradle/wrapper/gradle-wrapper.properties](/gradle/wrapper/gradle-wrapper.properties) | Properties | 5 | 0 | 1 | 6 |
| [gradlew.bat](/gradlew.bat) | Batch | 76 | 0 | 25 | 101 |
| [robotconfig.py](/robotconfig.py) | Python | 16 | 34 | 3 | 53 |
| [settings.gradle](/settings.gradle) | Groovy | 26 | 0 | 2 | 28 |
| [src/main/java/frc/robot/BoxPair.java](/src/main/java/frc/robot/BoxPair.java) | Java | 20 | 5 | 6 | 31 |
| [src/main/java/frc/robot/Constants.java](/src/main/java/frc/robot/Constants.java) | Java | 32 | 27 | 12 | 71 |
| [src/main/java/frc/robot/DropPoint.java](/src/main/java/frc/robot/DropPoint.java) | Java | 147 | 13 | 72 | 232 |
| [src/main/java/frc/robot/Globals.java](/src/main/java/frc/robot/Globals.java) | Java | 70 | 0 | 25 | 95 |
| [src/main/java/frc/robot/Main.java](/src/main/java/frc/robot/Main.java) | Java | 9 | 16 | 5 | 30 |
| [src/main/java/frc/robot/Node.java](/src/main/java/frc/robot/Node.java) | Java | 13 | 0 | 5 | 18 |
| [src/main/java/frc/robot/PathMap.java](/src/main/java/frc/robot/PathMap.java) | Java | 158 | 5 | 34 | 197 |
| [src/main/java/frc/robot/PathMap2.java](/src/main/java/frc/robot/PathMap2.java) | Java | 177 | 7 | 34 | 218 |
| [src/main/java/frc/robot/Pathfinder.java](/src/main/java/frc/robot/Pathfinder.java) | Java | 176 | 34 | 37 | 247 |
| [src/main/java/frc/robot/Points.java](/src/main/java/frc/robot/Points.java) | Java | 94 | 3 | 32 | 129 |
| [src/main/java/frc/robot/Robot.java](/src/main/java/frc/robot/Robot.java) | Java | 69 | 68 | 32 | 169 |
| [src/main/java/frc/robot/RobotContainer.java](/src/main/java/frc/robot/RobotContainer.java) | Java | 28 | 19 | 10 | 57 |
| [src/main/java/frc/robot/commands/TeleCmd.java](/src/main/java/frc/robot/commands/TeleCmd.java) | Java | 50 | 41 | 17 | 108 |
| [src/main/java/frc/robot/commands/auto/AlignDrop.java](/src/main/java/frc/robot/commands/auto/AlignDrop.java) | Java | 63 | 3 | 10 | 76 |
| [src/main/java/frc/robot/commands/auto/AlignLeft.java](/src/main/java/frc/robot/commands/auto/AlignLeft.java) | Java | 21 | 0 | 10 | 31 |
| [src/main/java/frc/robot/commands/auto/AlignNull.java](/src/main/java/frc/robot/commands/auto/AlignNull.java) | Java | 23 | 0 | 11 | 34 |
| [src/main/java/frc/robot/commands/auto/AlignPick.java](/src/main/java/frc/robot/commands/auto/AlignPick.java) | Java | 23 | 0 | 11 | 34 |
| [src/main/java/frc/robot/commands/auto/AlignRight.java](/src/main/java/frc/robot/commands/auto/AlignRight.java) | Java | 21 | 0 | 10 | 31 |
| [src/main/java/frc/robot/commands/auto/AutoCommand.java](/src/main/java/frc/robot/commands/auto/AutoCommand.java) | Java | 14 | 14 | 5 | 33 |
| [src/main/java/frc/robot/commands/auto/Deliver.java](/src/main/java/frc/robot/commands/auto/Deliver.java) | Java | 62 | 11 | 35 | 108 |
| [src/main/java/frc/robot/commands/auto/End.java](/src/main/java/frc/robot/commands/auto/End.java) | Java | 15 | 1 | 8 | 24 |
| [src/main/java/frc/robot/commands/auto/FollowLine.java](/src/main/java/frc/robot/commands/auto/FollowLine.java) | Java | 36 | 2 | 10 | 48 |
| [src/main/java/frc/robot/commands/auto/FollowPath.java](/src/main/java/frc/robot/commands/auto/FollowPath.java) | Java | 97 | 2 | 36 | 135 |
| [src/main/java/frc/robot/commands/auto/GetBin.java](/src/main/java/frc/robot/commands/auto/GetBin.java) | Java | 27 | 0 | 5 | 32 |
| [src/main/java/frc/robot/commands/auto/LoopOne.java](/src/main/java/frc/robot/commands/auto/LoopOne.java) | Java | 60 | 15 | 13 | 88 |
| [src/main/java/frc/robot/commands/auto/LoopTwo.java](/src/main/java/frc/robot/commands/auto/LoopTwo.java) | Java | 64 | 13 | 10 | 87 |
| [src/main/java/frc/robot/commands/auto/MainSequence.java](/src/main/java/frc/robot/commands/auto/MainSequence.java) | Java | 46 | 0 | 12 | 58 |
| [src/main/java/frc/robot/commands/auto/MapPose.java](/src/main/java/frc/robot/commands/auto/MapPose.java) | Java | 53 | 0 | 32 | 85 |
| [src/main/java/frc/robot/commands/auto/MoveArmXY.java](/src/main/java/frc/robot/commands/auto/MoveArmXY.java) | Java | 87 | 39 | 33 | 159 |
| [src/main/java/frc/robot/commands/auto/MovePose.java](/src/main/java/frc/robot/commands/auto/MovePose.java) | Java | 100 | 6 | 31 | 137 |
| [src/main/java/frc/robot/commands/auto/MovePose2.java](/src/main/java/frc/robot/commands/auto/MovePose2.java) | Java | 98 | 6 | 35 | 139 |
| [src/main/java/frc/robot/commands/auto/MovePose3.java](/src/main/java/frc/robot/commands/auto/MovePose3.java) | Java | 99 | 3 | 36 | 138 |
| [src/main/java/frc/robot/commands/auto/MoveRobot.java](/src/main/java/frc/robot/commands/auto/MoveRobot.java) | Java | 70 | 35 | 15 | 120 |
| [src/main/java/frc/robot/commands/auto/MoveRobotSense.java](/src/main/java/frc/robot/commands/auto/MoveRobotSense.java) | Java | 19 | 5 | 8 | 32 |
| [src/main/java/frc/robot/commands/auto/MoveRobotSense1.java](/src/main/java/frc/robot/commands/auto/MoveRobotSense1.java) | Java | 38 | 6 | 11 | 55 |
| [src/main/java/frc/robot/commands/auto/Pick.java](/src/main/java/frc/robot/commands/auto/Pick.java) | Java | 111 | 21 | 33 | 165 |
| [src/main/java/frc/robot/commands/auto/PickCommands/ArmPickX.java](/src/main/java/frc/robot/commands/auto/PickCommands/ArmPickX.java) | Java | 94 | 48 | 48 | 190 |
| [src/main/java/frc/robot/commands/auto/PickCommands/ArmPickY.java](/src/main/java/frc/robot/commands/auto/PickCommands/ArmPickY.java) | Java | 100 | 68 | 54 | 222 |
| [src/main/java/frc/robot/commands/auto/PickCommands/GripperPick.java](/src/main/java/frc/robot/commands/auto/PickCommands/GripperPick.java) | Java | 89 | 49 | 41 | 179 |
| [src/main/java/frc/robot/commands/auto/PickCommands/RobotPick.java](/src/main/java/frc/robot/commands/auto/PickCommands/RobotPick.java) | Java | 79 | 44 | 25 | 148 |
| [src/main/java/frc/robot/commands/auto/Reset.java](/src/main/java/frc/robot/commands/auto/Reset.java) | Java | 20 | 0 | 9 | 29 |
| [src/main/java/frc/robot/commands/auto/SequenceOne.java](/src/main/java/frc/robot/commands/auto/SequenceOne.java) | Java | 41 | 0 | 18 | 59 |
| [src/main/java/frc/robot/commands/auto/SequenceTwo.java](/src/main/java/frc/robot/commands/auto/SequenceTwo.java) | Java | 44 | 0 | 15 | 59 |
| [src/main/java/frc/robot/commands/auto/Tasks/MoveRobotSense2.java](/src/main/java/frc/robot/commands/auto/Tasks/MoveRobotSense2.java) | Java | 19 | 5 | 8 | 32 |
| [src/main/java/frc/robot/commands/auto/Tasks/OnlyKitkat.java](/src/main/java/frc/robot/commands/auto/Tasks/OnlyKitkat.java) | Java | 54 | 21 | 14 | 89 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task1.java](/src/main/java/frc/robot/commands/auto/Tasks/Task1.java) | Java | 14 | 0 | 4 | 18 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task2.java](/src/main/java/frc/robot/commands/auto/Tasks/Task2.java) | Java | 30 | 0 | 13 | 43 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task3.java](/src/main/java/frc/robot/commands/auto/Tasks/Task3.java) | Java | 10 | 0 | 4 | 14 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task4.java](/src/main/java/frc/robot/commands/auto/Tasks/Task4.java) | Java | 18 | 0 | 5 | 23 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task5.java](/src/main/java/frc/robot/commands/auto/Tasks/Task5.java) | Java | 19 | 0 | 5 | 24 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task6.java](/src/main/java/frc/robot/commands/auto/Tasks/Task6.java) | Java | 16 | 0 | 4 | 20 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task7.java](/src/main/java/frc/robot/commands/auto/Tasks/Task7.java) | Java | 18 | 0 | 9 | 27 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8b.java](/src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8b.java) | Java | 19 | 0 | 8 | 27 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8c.java](/src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8c.java) | Java | 19 | 0 | 8 | 27 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8k.java](/src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8k.java) | Java | 19 | 0 | 8 | 27 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8n.java](/src/main/java/frc/robot/commands/auto/Tasks/Task8/Task8n.java) | Java | 19 | 0 | 8 | 27 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task9Black.java](/src/main/java/frc/robot/commands/auto/Tasks/Task9Black.java) | Java | 44 | 0 | 6 | 50 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task9Blue.java](/src/main/java/frc/robot/commands/auto/Tasks/Task9Blue.java) | Java | 45 | 0 | 6 | 51 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task9Green.java](/src/main/java/frc/robot/commands/auto/Tasks/Task9Green.java) | Java | 43 | 0 | 5 | 48 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task9Red.java](/src/main/java/frc/robot/commands/auto/Tasks/Task9Red.java) | Java | 43 | 0 | 5 | 48 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task9Yellow.java](/src/main/java/frc/robot/commands/auto/Tasks/Task9Yellow.java) | Java | 43 | 0 | 5 | 48 |
| [src/main/java/frc/robot/commands/auto/Test.java](/src/main/java/frc/robot/commands/auto/Test.java) | Java | 28 | 17 | 15 | 60 |
| [src/main/java/frc/robot/commands/auto/ZeroPose.java](/src/main/java/frc/robot/commands/auto/ZeroPose.java) | Java | 42 | 0 | 24 | 66 |
| [src/main/java/frc/robot/commands/gamepad/GamepadConstants.java](/src/main/java/frc/robot/commands/gamepad/GamepadConstants.java) | Java | 25 | 5 | 4 | 34 |
| [src/main/java/frc/robot/commands/gamepad/OI.java](/src/main/java/frc/robot/commands/gamepad/OI.java) | Java | 116 | 60 | 23 | 199 |
| [src/main/java/frc/robot/pathfinding/AStarAlgorithm.java](/src/main/java/frc/robot/pathfinding/AStarAlgorithm.java) | Java | 128 | 6 | 39 | 173 |
| [src/main/java/frc/robot/pathfinding/element/Grid.java](/src/main/java/frc/robot/pathfinding/element/Grid.java) | Java | 82 | 5 | 12 | 99 |
| [src/main/java/frc/robot/pathfinding/element/Network.java](/src/main/java/frc/robot/pathfinding/element/Network.java) | Java | 4 | 0 | 3 | 7 |
| [src/main/java/frc/robot/pathfinding/element/Node.java](/src/main/java/frc/robot/pathfinding/element/Node.java) | Java | 61 | 0 | 24 | 85 |
| [src/main/java/frc/robot/pathfinding/element/Tile.java](/src/main/java/frc/robot/pathfinding/element/Tile.java) | Java | 76 | 11 | 22 | 109 |
| [src/main/java/frc/robot/subsystems/Arm.java](/src/main/java/frc/robot/subsystems/Arm.java) | Java | 89 | 45 | 31 | 165 |
| [src/main/java/frc/robot/subsystems/Menu.java](/src/main/java/frc/robot/subsystems/Menu.java) | Java | 119 | 3 | 13 | 135 |
| [src/main/java/frc/robot/subsystems/OmniDrive.java](/src/main/java/frc/robot/subsystems/OmniDrive.java) | Java | 214 | 104 | 66 | 384 |
| [src/main/java/frc/robot/subsystems/Sensor.java](/src/main/java/frc/robot/subsystems/Sensor.java) | Java | 98 | 83 | 46 | 227 |
| [src/main/java/frc/robot/subsystems/Vision.java](/src/main/java/frc/robot/subsystems/Vision.java) | Java | 162 | 43 | 81 | 286 |
| [vendordeps/Studica.json](/vendordeps/Studica.json) | JSON | 64 | 0 | 0 | 64 |
| [vendordeps/WPILibNewCommands.json](/vendordeps/WPILibNewCommands.json) | JSON | 37 | 0 | 1 | 38 |
| [vendordeps/navx_frc.json](/vendordeps/navx_frc.json) | JSON | 35 | 0 | 0 | 35 |

[summary](results.md)