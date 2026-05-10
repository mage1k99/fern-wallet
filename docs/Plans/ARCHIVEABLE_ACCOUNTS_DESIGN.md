# High-Level & Low-Level Design: Archiveable Accounts

## 1. Overview
Archiving an account allows users to hide accounts they no longer actively use (e.g., closed bank accounts or old wallets) without deleting their transaction history. This keeps the account selection and home screens clean while maintaining financial records.

### Requirements:
- Users can archive/unarchive accounts from the account details screen or the account edit modal.
- Archived accounts are hidden from the account selection list (bottom sheet) when adding/editing transactions.
- Archiving does not delete the account or its associated transactions.
- Users can toggle the visibility of archived accounts in the main Accounts list.
- Archived accounts in the list are visually distinguished (lower opacity).

---

## 2. High-Level Design (HLD)

### User Journey
1. **Archiving**:
    - **Method A**: User navigates to Account Details (Home -> Accounts -> Select Account). A new **Archive** (eye) icon is available in the toolbar.
    - **Method B**: User edits an account and toggles the **Archive** checkbox in the modal.
2. **Unarchiving**: Toggling the same icon or checkbox restores the account to an active state.
3. **Visibility**:
    - **Account List**: A new "Visibility" (eye) icon next to the reorder button toggles whether archived accounts are shown.
    - **Transaction Entry**: Archived accounts are filtered out from the "Select Account" sheet, unless a transaction is already associated with that archived account.
    - **Home/Balance**: Archived accounts are typically excluded from active views unless explicitly shown.

---

## 3. Low-Level Design (LLD)

### 3.1 Data Layer Changes
- **`AccountEntity`**: Add `isArchived: Boolean = false`.
- **`Account` (Domain Model)**: Add `isArchived: Boolean`.
- **Room Migration**: Increment `IvyRoomDatabase` version to `132` and add `Migration131to132_ArchiveAccounts`:
  `ALTER TABLE accounts ADD COLUMN isArchived INTEGER NOT NULL DEFAULT 0`.

### 3.2 Domain / Repository Layer
- **`AccountRepository`**: `save()` handles the updated `isArchived` state.
- **`AccountsAct`**: Continues to return all accounts; filtering happens at the ViewModel level based on UI state.

### 3.3 UI Layer Changes
- **Resources**: Uses `ic_visible.xml` and `ic_hidden.xml` for the list toggle, and `ic_hide_m.xml` for the archive action.
- **`AccountsTab`**:
    - Added a toggle button in the header to control `showArchived` state.
    - Updated `AccountCard` to set `alpha = 0.5f` if `account.isArchived`.
- **`AccountModal`**:
    - Added an "Archive" checkbox.
    - Updated `save` logic to pass the `isArchived` flag to `AccountCreator`.
- **`EditTransactionViewModel`**:
    - Filters the `accounts` list: `accounts.filter { !it.isArchived || it.id == selectedAccountId }`.
- **`TransactionsScreen`**:
    - Updated `Header` and `ItemStatisticToolbar` to support the archive action for accounts.

---

## 4. Implementation Plan

### Step 1: Database & Entity Update
1. Update `AccountEntity`, `Account` (Domain & Legacy), and `AccountMapper`.
2. Implement `Migration131to132_ArchiveAccounts` and register it in `IvyRoomDatabase.kt`.

### Step 2: ViewModel Logic
1. Update `AccountsViewModel` to hold `showArchived` state and handle `OnToggleShowArchived` event.
2. Update `EditTransactionViewModel` and `TransactionsViewModel` to filter account lists based on the archived flag.

### Step 3: UI Integration
1. Add the toggle button to `AccountsTab`.
2. Add the archive checkbox to `AccountModal`.
3. Update `TransactionsScreen` toolbar to show the archive button when viewing an account.

---

## 5. Verification & Testing

### 5.1 Unit Tests (UTs)
- **`AccountMapperTest`**: Verify `isArchived` is correctly mapped between Entity and Domain models.
- **`AccountsViewModelTest`**: Verify that `showArchived` correctly filters the accounts data.

### 5.2 Functional Tests (FTs)
- **Archiving**: Open an account, tap the archive icon, and verify it disappears from the "Add Expense" account list.
- **Toggle Visibility**: In the Accounts tab, tap the eye icon and verify archived accounts appear/disappear.
- **Visuals**: Ensure archived accounts are correctly "grayed out" (50% alpha) when visible.

---

## 6. Documentation
- Update `CHANGELOG.md`: "Feature: Archive accounts to declutter the app while preserving transaction history."
