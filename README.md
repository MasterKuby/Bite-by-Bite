# Bite by Bite

### *A Minecraft Forge mod that adds multi-bite food items with visual progression - watch your food disappear bite by bite!*

[![Modrinth](https://img.shields.io/badge/Modrinth-Download-00AF5C?style=for-the-badge&logo=modrinth)](https://modrinth.com/mod/bite-by-bite)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](https://github.com/MasterKuby/Bite-by-Bite/blob/mc/forge/1.20.1/LICENSE.txt)

## Features
- **Multi-bite eating mechanic**: Food items are consumed in multiple bites instead of instantly
- **Dynamic 3D models**: Items visually change as you eat them, showing remaining portions
- **Extensible framework**: Easy to add new multi-bite food items

## Items
*(screenshots here)*
- 🍡 Mochi Mochi
- 🍢 Kebab (soon)
- 🍉 Sliceable Watermelon (soon)

## How to use
1. Install Minecraft Forge for version 1.20.1
2. Drop the mod JAR into your `mods` folder
3. Launch Minecraft with Forge profile

---

## Technical Details
- **Base Class**: `MultiBiteFoodItem` - handles bite tracking and model progression
- **Model System**: Uses Minecraft's item override predicates with custom bite counter
- **NBT Tags**: Tracks remaining bites in `BitesRemaining` tag
- **Extensibility**: Create new items by extending `MultiBiteFoodItem`

## Requirements
- Minecraft [1.20.1]
- Forge [47.4.XX]
