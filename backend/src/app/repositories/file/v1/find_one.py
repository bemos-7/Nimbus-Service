async def find_one(self, user_folder: str, filename: str) -> str:
    return await self.local_file_manager.get_file(user_folder, filename)
