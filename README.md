# Assignment 3 | Bridge Pattern — Topic D: Remote controls

Student: Sulukhan Aizharykova
Group: SE-2529
Topic: D — Remote controls
Repository: https://github.com/SulukhanAizharykova/ASS3_Sulukhan_Aizharykova_SE_2529_SDP
Base commit (working I1/I2 version): b4a571705e5fb8caf06470b004a9c8250e0ee7f7

## Design and role map

The two independent dimensions are remote-control behavior (BasicRemote at volume 30 and QuietRemote at volume 5) and device type (TV, radio, and projector). The interface-typed `Device` reference in `Remote` connects these dimensions by composition.

| Bridge role | Class | Source |
|---|---|---|
| Abstraction | `Remote` | `src/Remote.java` |
| A1 — refined abstraction | `BasicRemote` | `src/BasicRemote.java` |
| A2 — refined abstraction | `QuietRemote` | `src/QuietRemote.java` |
| Implementor | `Device` | `src/Device.java` |
| I1 | `TvDevice` | `src/TvDevice.java` |
| I2 | `RadioDevice` | `src/RadioDevice.java` |
| I3 — extension | `ProjectorDevice` | `src/ProjectorDevice.java` |
| Shared state and formatting helper | `DeviceState` | `src/DeviceState.java` |
| Client and checks | `Main` | `src/Main.java` |

Key locations: `Remote.implementation` is the interface-typed bridge field; `BasicRemote.execute()` and `QuietRemote.execute()` call `Remote.applyPreset()`, which delegates to `Device.applySettings(int)`. `TvDevice`, `RadioDevice`, and `ProjectorDevice` each implement `Device` directly and use `DeviceState` to avoid repeated state and formatting code. `Remote.setImplementation(Device)` replaces the device. `Main.runDemo()` contains T5, including reference equality (`==`) and ID/preset preservation checks.

## Compile and run

From the extracted project folder, using JDK 17 or later:

```text
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

No IDE, external library, hardware, network, or interactive input is needed.

## Expected outcomes

| Check | Classes/action | Expected result |
|---|---|---|
| T1 | BasicRemote + TvDevice | `TV power=ON volume=30` |
| T2 | BasicRemote + RadioDevice | `RADIO power=ON volume=30` |
| T3 | QuietRemote + TvDevice | `TV power=ON volume=5` |
| T4 | QuietRemote + RadioDevice | `RADIO power=ON volume=5` |
| T5 | The same BasicRemote object switches from TvDevice to RadioDevice | `sameObject=true`, `stateUnchanged=true`, before `TV power=ON volume=30`, after `RADIO power=ON volume=30` |
| T6 | BasicRemote + ProjectorDevice | `PROJECTOR power=ON volume=30` |
| T7 | QuietRemote + ProjectorDevice | `PROJECTOR power=ON volume=5` |

The demo compares actual results with these expected values and calculates PASS/FAIL and the final summary. `demo-output.txt` records the submitted program's real console output.

## Independent extension

The base commit contains the working I1/I2 implementation and T1–T5. The extension adds `src/ProjectorDevice.java` and updates only `src/Main.java` among existing Java sources. The abstraction classes, `Device`, `TvDevice`, and `RadioDevice` remain unchanged. `extension.diff` records the Java source changes from the base commit to the submitted commit.

The commit timestamps were reconstructed after the original local work was lost, with instructor permission. The base and extension commits record the corresponding source milestones.

## Sources and attribution

- Almas Ospanov, *Lecture 4: The Structural Design Pattern — Bridge Pattern*, course materials.
- Yerassyl Bekenov, *Assignment 3 | Bridge Pattern*, Astana IT University, 2026.
- This draft was prepared with AI assistance as a reference implementation; review its use against the course syllabus and be able to explain every line before submission.
