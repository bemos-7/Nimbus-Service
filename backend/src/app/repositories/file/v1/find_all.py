async def find_all(self, user_folder: str) -> str:
    return await self.local_file_manager.list_files(user_folder)
