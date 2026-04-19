# High-Level & Low-Level Design: Archiveable Categories

## 1. Overview
Archiving a category allows users to "soft-hide" categories they no longer use without losing the transaction history associated with them. This avoids the "uncategorized" problem that occurs with deletion and declutters the category selection page.

### Requirements:
- Users can archive/unarchive categories from the category details screen.
- Archived categories are hidden from the category selection screen when adding/editing transactions.
- Archiving does not delete the category or affect existing transactions.
- Archived categories remain visible in the main categories list but should be visually distinguished.

---

## 2. High-Level Design (HLD)

### User Journey
1. **Archiving**: User navigates to Category Details (Settings -> Categories -> Select Category). A new **Archive** icon is added next to the **Delete** icon in the toolbar.
2. **Unarchiving**: If a category is already archived, the icon changes to **Unarchive** (toggled state).
3. **Visibility**:
    - **Transaction Entry**: Archived categories are filtered out from the "Select Category" modal.
    - **Category Management**: Archived categories remain visible in the main list (Settings -> Categories) but are styled with lower opacity (alpha).
    - **Reports**: Transactions under archived categories still appear in reports to maintain financial accuracy.

---

## 3. Low-Level Design (LLD)

### 3.1 Data Layer Changes
- **`CategoryEntity`**: Add `isArchived: Boolean = false`.
- **`Category` (Domain Model)**: Add `isArchived: Boolean`.
- **Decision (Boolean vs Enum)**: We chose `isArchived: Boolean` for consistency with existing flags like `isDeleted` and `includeInBalance`.
- **Room Migration**: Increment `IvyRoomDatabase` version and add a migration:
  `ALTER TABLE categories ADD COLUMN isArchived INTEGER NOT NULL DEFAULT 0`.

### 3.2 Domain / Repository Layer
- **`CategoryRepository`**: No major changes; `save()` handles updates.
- **Filtering**: ViewModels for transaction creation must filter categories by `!isArchived`.

### 3.3 UI Layer Changes
- **Resources**: Use `shared/ui/core/src/main/res/drawable/ic_vue_main_archive.xml`.
- **Components**: Create an `ArchiveButton` (referencing `DeleteButton` implementation).
  - **Color**: Use a neutral gray. Specifically, `IvyColors.Gray` or a gray gradient `Gradient(Gray, LightGray)`.
- **`ItemStatisticToolbar` (`shared/ui/legacy`)**: 
    - Add `onArchive: () -> Unit` and `isArchived: Boolean`.
    - Place `ArchiveButton` to the left of the `DeleteButton`.
- **`TransactionsScreen` (Category Details mode)**:
    - Pass `isArchived` and `onArchive` to `Header` and `ItemStatisticToolbar`.
- **`CategoriesScreen`**:
    - Update `CategoryCard` to set `alpha = 0.5f` if `category.isArchived`.
- **`ChooseCategoryModal`**:
    - Filter the category list: `categories.filter { !it.isArchived }`.

---

## 4. Implementation Plan

### Step 1: Database & Entity Update
1. Update `CategoryEntity`, `Category`, and `CategoryMapper`.
2. Add migration to `IvyRoomDatabase.kt`.

### Step 2: UI Component (`ArchiveButton`)
- In `temp/legacy-code/.../components/`, create `ArchiveButton.kt`:
```kotlin
@Composable
fun ArchiveButton(isArchived: Boolean, onClick: () -> Unit) {
    IvyCircleButton(
        icon = R.drawable.ic_vue_main_archive,
        backgroundGradient = if (isArchived) GradientIvy else Gradient.solid(UI.colors.gray),
        tint = White,
        onClick = onClick
    )
}
```

### Step 3: ViewModel & Event logic
1. Add `ToggleArchiveCategory` to `TransactionsEvent`.
2. Update `TransactionsViewModel` to handle the event by saving the toggled category state.

### Step 4: Integration
1. Update `ItemStatisticToolbar` to include the new button.
2. Update `ChooseCategoryModal` to apply the filter.
3. Update `CategoriesScreen` for the visual "archived" state.

---

## 5. Verification & Testing

### 5.1 Unit Tests (UTs)
- **`CategoryMapperTest`**: Verify `isArchived` mapping.
- **`TransactionsViewModelTest`**: Verify the archive toggle logic.

### 5.2 Functional Tests (FTs)
- **Archiving**: Open category, tap archive, check if it disappears from "Add Expense" list.
- **Unarchiving**: Open archived category from Settings, tap unarchive, check if it reappears.
- **Visuals**: Ensure archived categories in the main list are clearly "grayed out" via alpha.

---

## 6. Documentation
- Update `CHANGELOG.md`: "Feature: Archive categories to hide them from selection while keeping history."
