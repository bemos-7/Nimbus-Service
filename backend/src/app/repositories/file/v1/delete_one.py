async def delete_one(self, user_folder: str, filename: str) -> str:
    return await self.local_file_manager.delete_one(user_folder, filename)
