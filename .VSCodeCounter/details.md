# Details

Date : 2021-09-20 13:49:20

Directory c:\Users\carlc\Desktop\WSS\WSS code\OmniDrive-Carl

Total : 66 files,  3559 codes, 941 comments, 1223 blanks, all 5723 lines

[summary](results.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [.gitattributes](/.gitattributes) | Properties | 1 | 1 | 1 | 3 |
| [.wpilib/wpilib_preferences.json](/.wpilib/wpilib_preferences.json) | JSON | 6 | 0 | 4 | 10 |
| [build.gradle](/build.gradle) | Groovy | 40 | 18 | 11 | 69 |
| [gradle/wrapper/gradle-wrapper.properties](/gradle/wrapper/gradle-wrapper.properties) | Properties | 5 | 0 | 1 | 6 |
| [gradlew.bat](/gradlew.bat) | Batch | 76 | 0 | 25 | 101 |
| [robotconfig.py](/robotconfig.py) | Python | 16 | 34 | 3 | 53 |
| [settings.gradle](/settings.gradle) | Groovy | 26 | 0 | 2 | 28 |
| [src/main/java/frc/robot/BoxPair.java](/src/main/java/frc/robot/BoxPair.java) | Java | 20 | 5 | 6 | 31 |
| [src/main/java/frc/robot/Constants.java](/src/main/java/frc/robot/Constants.java) | Java | 26 | 27 | 13 | 66 |
| [src/main/java/frc/robot/DropPoint.java](/src/main/java/frc/robot/DropPoint.java) | Java | 170 | 6 | 58 | 234 |
| [src/main/java/frc/robot/Globals.java](/src/main/java/frc/robot/Globals.java) | Java | 66 | 0 | 26 | 92 |
| [src/main/java/frc/robot/Main.java](/src/main/java/frc/robot/Main.java) | Java | 9 | 16 | 5 | 30 |
| [src/main/java/frc/robot/Node.java](/src/main/java/frc/robot/Node.java) | Java | 13 | 0 | 5 | 18 |
| [src/main/java/frc/robot/PathMap.java](/src/main/java/frc/robot/PathMap.java) | Java | 87 | 5 | 21 | 113 |
| [src/main/java/frc/robot/Pathfinder.java](/src/main/java/frc/robot/Pathfinder.java) | Java | 176 | 34 | 37 | 247 |
| [src/main/java/frc/robot/Points.java](/src/main/java/frc/robot/Points.java) | Java | 80 | 3 | 33 | 116 |
| [src/main/java/frc/robot/Robot.java](/src/main/java/frc/robot/Robot.java) | Java | 70 | 67 | 31 | 168 |
| [src/main/java/frc/robot/RobotContainer.java](/src/main/java/frc/robot/RobotContainer.java) | Java | 28 | 19 | 10 | 57 |
| [src/main/java/frc/robot/commands/TeleCmd.java](/src/main/java/frc/robot/commands/TeleCmd.java) | Java | 42 | 41 | 16 | 99 |
| [src/main/java/frc/robot/commands/auto/AlignDrop.java](/src/main/java/frc/robot/commands/auto/AlignDrop.java) | Java | 49 | 2 | 12 | 63 |
| [src/main/java/frc/robot/commands/auto/AlignLeft.java](/src/main/java/frc/robot/commands/auto/AlignLeft.java) | Java | 21 | 0 | 10 | 31 |
| [src/main/java/frc/robot/commands/auto/AlignRight.java](/src/main/java/frc/robot/commands/auto/AlignRight.java) | Java | 21 | 0 | 10 | 31 |
| [src/main/java/frc/robot/commands/auto/AutoCommand.java](/src/main/java/frc/robot/commands/auto/AutoCommand.java) | Java | 14 | 14 | 5 | 33 |
| [src/main/java/frc/robot/commands/auto/CheckItem.java](/src/main/java/frc/robot/commands/auto/CheckItem.java) | Java | 23 | 0 | 12 | 35 |
| [src/main/java/frc/robot/commands/auto/CommandSchedule.java](/src/main/java/frc/robot/commands/auto/CommandSchedule.java) | Java | 36 | 0 | 19 | 55 |
| [src/main/java/frc/robot/commands/auto/Deliver.java](/src/main/java/frc/robot/commands/auto/Deliver.java) | Java | 57 | 11 | 35 | 103 |
| [src/main/java/frc/robot/commands/auto/End.java](/src/main/java/frc/robot/commands/auto/End.java) | Java | 15 | 1 | 8 | 24 |
| [src/main/java/frc/robot/commands/auto/FollowLine.java](/src/main/java/frc/robot/commands/auto/FollowLine.java) | Java | 36 | 2 | 10 | 48 |
| [src/main/java/frc/robot/commands/auto/FollowPath.java](/src/main/java/frc/robot/commands/auto/FollowPath.java) | Java | 60 | 1 | 16 | 77 |
| [src/main/java/frc/robot/commands/auto/MapPose.java](/src/main/java/frc/robot/commands/auto/MapPose.java) | Java | 53 | 10 | 28 | 91 |
| [src/main/java/frc/robot/commands/auto/MoveArmXY.java](/src/main/java/frc/robot/commands/auto/MoveArmXY.java) | Java | 87 | 39 | 33 | 159 |
| [src/main/java/frc/robot/commands/auto/MovePose.java](/src/main/java/frc/robot/commands/auto/MovePose.java) | Java | 95 | 6 | 33 | 134 |
| [src/main/java/frc/robot/commands/auto/MovePose2.java](/src/main/java/frc/robot/commands/auto/MovePose2.java) | Java | 98 | 6 | 35 | 139 |
| [src/main/java/frc/robot/commands/auto/MoveRobot.java](/src/main/java/frc/robot/commands/auto/MoveRobot.java) | Java | 69 | 35 | 15 | 119 |
| [src/main/java/frc/robot/commands/auto/MoveRobotSense.java](/src/main/java/frc/robot/commands/auto/MoveRobotSense.java) | Java | 19 | 5 | 8 | 32 |
| [src/main/java/frc/robot/commands/auto/MoveRobotSense1.java](/src/main/java/frc/robot/commands/auto/MoveRobotSense1.java) | Java | 38 | 6 | 11 | 55 |
| [src/main/java/frc/robot/commands/auto/MoveTest2.java](/src/main/java/frc/robot/commands/auto/MoveTest2.java) | Java | 58 | 13 | 12 | 83 |
| [src/main/java/frc/robot/commands/auto/Pick.java](/src/main/java/frc/robot/commands/auto/Pick.java) | Java | 98 | 22 | 37 | 157 |
| [src/main/java/frc/robot/commands/auto/PickCommands/ArmPickX.java](/src/main/java/frc/robot/commands/auto/PickCommands/ArmPickX.java) | Java | 94 | 48 | 48 | 190 |
| [src/main/java/frc/robot/commands/auto/PickCommands/ArmPickY.java](/src/main/java/frc/robot/commands/auto/PickCommands/ArmPickY.java) | Java | 105 | 64 | 53 | 222 |
| [src/main/java/frc/robot/commands/auto/PickCommands/GripperPick.java](/src/main/java/frc/robot/commands/auto/PickCommands/GripperPick.java) | Java | 88 | 49 | 41 | 178 |
| [src/main/java/frc/robot/commands/auto/PickCommands/RobotPick.java](/src/main/java/frc/robot/commands/auto/PickCommands/RobotPick.java) | Java | 79 | 44 | 25 | 148 |
| [src/main/java/frc/robot/commands/auto/Reset.java](/src/main/java/frc/robot/commands/auto/Reset.java) | Java | 20 | 0 | 9 | 29 |
| [src/main/java/frc/robot/commands/auto/Tasks/MoveRobotSense2.java](/src/main/java/frc/robot/commands/auto/Tasks/MoveRobotSense2.java) | Java | 19 | 5 | 8 | 32 |
| [src/main/java/frc/robot/commands/auto/Tasks/OnlyKitkat.java](/src/main/java/frc/robot/commands/auto/Tasks/OnlyKitkat.java) | Java | 65 | 7 | 13 | 85 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task1.java](/src/main/java/frc/robot/commands/auto/Tasks/Task1.java) | Java | 10 | 0 | 4 | 14 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task2.java](/src/main/java/frc/robot/commands/auto/Tasks/Task2.java) | Java | 30 | 0 | 13 | 43 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task3.java](/src/main/java/frc/robot/commands/auto/Tasks/Task3.java) | Java | 10 | 0 | 4 | 14 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task4.java](/src/main/java/frc/robot/commands/auto/Tasks/Task4.java) | Java | 17 | 0 | 5 | 22 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task5.java](/src/main/java/frc/robot/commands/auto/Tasks/Task5.java) | Java | 20 | 0 | 5 | 25 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task6.java](/src/main/java/frc/robot/commands/auto/Tasks/Task6.java) | Java | 14 | 0 | 4 | 18 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task7.java](/src/main/java/frc/robot/commands/auto/Tasks/Task7.java) | Java | 18 | 0 | 9 | 27 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task8.java](/src/main/java/frc/robot/commands/auto/Tasks/Task8.java) | Java | 19 | 0 | 8 | 27 |
| [src/main/java/frc/robot/commands/auto/Tasks/Task9.java](/src/main/java/frc/robot/commands/auto/Tasks/Task9.java) | Java | 4 | 0 | 3 | 7 |
| [src/main/java/frc/robot/commands/auto/Test.java](/src/main/java/frc/robot/commands/auto/Test.java) | Java | 20 | 20 | 13 | 53 |
| [src/main/java/frc/robot/commands/auto/ZeroPose.java](/src/main/java/frc/robot/commands/auto/ZeroPose.java) | Java | 42 | 0 | 24 | 66 |
| [src/main/java/frc/robot/commands/gamepad/GamepadConstants.java](/src/main/java/frc/robot/commands/gamepad/GamepadConstants.java) | Java | 25 | 5 | 4 | 34 |
| [src/main/java/frc/robot/commands/gamepad/OI.java](/src/main/java/frc/robot/commands/gamepad/OI.java) | Java | 114 | 60 | 23 | 197 |
| [src/main/java/frc/robot/subsystems/Arm.java](/src/main/java/frc/robot/subsystems/Arm.java) | Java | 97 | 37 | 31 | 165 |
| [src/main/java/frc/robot/subsystems/Menu.java](/src/main/java/frc/robot/subsystems/Menu.java) | Java | 77 | 3 | 15 | 95 |
| [src/main/java/frc/robot/subsystems/OmniDrive.java](/src/main/java/frc/robot/subsystems/OmniDrive.java) | Java | 218 | 81 | 68 | 367 |
| [src/main/java/frc/robot/subsystems/Sensor.java](/src/main/java/frc/robot/subsystems/Sensor.java) | Java | 110 | 65 | 40 | 215 |
| [src/main/java/frc/robot/subsystems/Vision.java](/src/main/java/frc/robot/subsystems/Vision.java) | Java | 204 | 4 | 95 | 303 |
| [vendordeps/Studica.json](/vendordeps/Studica.json) | JSON | 64 | 0 | 0 | 64 |
| [vendordeps/WPILibNewCommands.json](/vendordeps/WPILibNewCommands.json) | JSON | 37 | 0 | 1 | 38 |
| [vendordeps/navx_frc.json](/vendordeps/navx_frc.json) | JSON | 35 | 0 | 0 | 35 |

[summary](results.md)