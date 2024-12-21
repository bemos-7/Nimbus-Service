from fastapi import UploadFile


async def create_one(self, user_folder: str, file: UploadFile) -> str:
    file_content = await file.read()
    return await self.local_file_manager.save_file(user_folder, file.filename, file_content)
